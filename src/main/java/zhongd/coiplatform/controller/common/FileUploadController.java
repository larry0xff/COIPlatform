package zhongd.coiplatform.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.common.FileService;
import zhongd.coiplatform.utils.FileTypeConstant;
import zhongd.coiplatform.utils.ReturnCode;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  15:52 星期三 2017/12/6/006
 */
@Controller
@RequestMapping(value = "/upload")
@ResponseBody
public class FileUploadController extends BaseController{

    @Autowired
    FileService fileService;

    /**
     * 上传文件
     * @param file
     * @param type 1:excel 2:img 3:compress
     * @return
     */
    @PostMapping("/file")
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
}
