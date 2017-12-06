package zhongd.coiplatform.controller.common;

import com.sun.org.apache.bcel.internal.generic.RETURN;
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
     * 上传excel表格
     * @param file
     * @return
     */
    @PostMapping("/excel")
    public ReturnObj uploadExcelFile(@RequestParam("file") MultipartFile file){
        ReturnObj obj = new ReturnObj();
        try{
            if(file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")){
                obj.setData(fileService.save(file));
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setMsg("文件格式有误！");
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("上传表格失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 上传图片（JPG/JPEG/PNG）
     * @param img
     * @return
     */
    @PostMapping("/img")
    public ReturnObj uploadImgFile(@RequestParam("img") MultipartFile img){
        ReturnObj obj = new ReturnObj();
        try{
            String filename = img.getOriginalFilename();
            if(filename.endsWith(".jpg") || filename.endsWith(".jpeg")
                    || filename.endsWith(".png")){
                obj.setData(fileService.save(img));
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setMsg("图片格式有误失败！请确保为jpg、jpeg、png格式");
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("上传图片失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
