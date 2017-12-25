package zhongd.coiplatform.schedule.handler;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhongd.coiplatform.entity.DO.advice.IgAdviceCollection;
import zhongd.coiplatform.entity.DO.advice.IgAdviceRecord;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceRecordDTO;
import zhongd.coiplatform.service.advice.IgAdviceService;
import zhongd.coiplatform.utils.DateUtil;
import zhongd.coiplatform.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  23:28 星期六 2017/12/16/016
 */
@Service
public class Daily0Handler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${file.downloadpath}")
    String downloadpath;

    @Autowired
    IgAdviceService igAdviceService;

    @Scheduled(cron = "0 0 * * * ?")
    @Async
    public void execute(){
        try{
            handleAdviceCollection();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 处理到期征集记录
     */
    @Transactional
    public void handleAdviceCollection() throws Exception {
        //获取所有未到期的征集记录
        List<IgAdviceCollection> collectionList = igAdviceService.getAllCollectingCollection();
        Date now = new Date();
        for(IgAdviceCollection collection : collectionList){
            logger.info(DateUtil.diff(collection.getDeadline(), now, Calendar.SECOND) +"");
            if(DateUtil.diff(collection.getDeadline(), now, Calendar.SECOND) >= 0){
                String filename = createRecordsFile(collection.getIgAdviceCollectionId());
                collection.setStatus(2);
                collection.setAdvicesAttachmentUrl(filename);
                igAdviceService.baseSave(collection);
            }
        }
    }

    /**
     * 生成收集到的意见的文件
     * @param igAdviceCollectionId
     */
    @Transactional
    public String createRecordsFile(Integer igAdviceCollectionId) throws Exception {
        List<IgAdviceRecordDTO> list = igAdviceService.getRecordsByCollectionId(igAdviceCollectionId);
        File file = new File(downloadpath + StringUtil.getUUIDString() + ".xlsx");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        // 生成第一行标题
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("编号");
        header.createCell(1).setCellValue("主题");
        header.createCell(2).setCellValue("意见发表者");
        header.createCell(3).setCellValue("意见内容");
        header.createCell(4).setCellValue("时间");
        header.setHeight((short)600);
        for(int i = 0; i < list.size(); i++){
            Row row = sheet.createRow(i + 1);
            row.setHeight((short)550);
            IgAdviceRecordDTO r = list.get(i);
            row.createCell(0).setCellValue(r.getIgAdviceRecordId() + "");
            row.createCell(1).setCellValue(r.getCollectionSubject());
            row.createCell(2).setCellValue(r.getCreateBy());
            row.createCell(3).setCellValue(r.getContent());
            row.createCell(4).setCellValue(r.getCreateTime());
        }

        wb.write(fos);
        fos.close();
        return file.getName();
    }
}
