package zhongd.coiplatform.controller.common;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.common.FileService;
import zhongd.coiplatform.utils.StringUtil;
import zhongd.coiplatform.utils.constant.FileTypeConstant;
import zhongd.coiplatform.utils.constant.ReturnCode;

import java.io.File;
import java.util.UUID;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  15:52 星期三 2017/12/6/006
 */
@Controller
@RequestMapping(value = "/file")
@ResponseBody
public class FileController extends BaseController{

    @Autowired
    FileService fileService;

    /**
     * 上传文件
     * @param file
     * @param type 1:excel 2:img 3:compress
     * @return
     */
    @PostMapping("/upload")
    public ReturnObj uploadExcelFile(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type){
        ReturnObj obj = new ReturnObj();
        try{
            String filename = file.getOriginalFilename();
            switch (type){
                case FileTypeConstant.FILE_EXCEL: {
                    if(file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")){
                        obj.setData(fileService.save(file));
                        obj.setReturnCode(ReturnCode.SUCCESS);
                    }else{
                        obj.setMsg("Excel文件格式有误！请确保文件格式为xls/xlsx");
                        obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                    }
                    break;
                }
                case FileTypeConstant.FILE_IMG: {
                    if(filename.endsWith(".jpg") || filename.endsWith(".jpeg")
                            || filename.endsWith(".png")){
                        obj.setData(fileService.save(file));
                        obj.setReturnCode(ReturnCode.SUCCESS);
                    }else{
                        obj.setMsg("图片格式有误,上传失败！请确保文件格式为jpg/jpeg/png");
                        obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                    }
                    break;
                }
                case FileTypeConstant.FILE_COMPRESS: {
                    if(filename.endsWith(".zip") || filename.endsWith(".rar")
                            || filename.endsWith(".7z")){
                        obj.setData(fileService.save(file));
                        obj.setReturnCode(ReturnCode.SUCCESS);
                    }else{
                        obj.setMsg("压缩文件格式有误，上传失败！请确保文件格式为zip/rar/7z");
                        obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                    }
                    break;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("上传失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 下载文件
     * @param filename 文件名
     * @param showname 下载时显示的名称
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(String filename, String showname){
        ResponseEntity<byte[]> data = null;
        try{
            File file = fileService.get(filename);
            if (StringUtil.isEmpty(showname)) {
                showname = UUID.randomUUID().toString();
            }
            HttpHeaders headers = new HttpHeaders();
            //下载显示的文件名，解决中文名称乱码问题
            String downloadFielName = new String(showname.getBytes("UTF-8"),"iso-8859-1");
            //通知浏览器以attachment（下载方式）打开图片
            headers.setContentDispositionFormData("attachment", downloadFielName);
            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            data = new ResponseEntity<>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return data;
    }
}
