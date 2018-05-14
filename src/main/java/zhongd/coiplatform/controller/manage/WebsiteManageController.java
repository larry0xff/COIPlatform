package zhongd.coiplatform.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.manage.IgSwitchService;
import zhongd.coiplatform.utils.constant.ReturnCode;

/**
 * @Author xiezd
 * @Date 2018-01-01 13:26
 * @Description
 */
@RestController
@RequestMapping("/manage")
public class WebsiteManageController extends BaseController{
    @Autowired
    IgSwitchService igSwitchService;

    @GetMapping("/switch/listA")
    public ReturnObj getSwitchList(){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igSwitchService.getSwitchList());
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取系统开关失败！");
        }
        return obj;
    }

    @GetMapping("/switch/changeStatus")
    public ReturnObj changeStatus(Integer igSwitchId, Integer status){
        ReturnObj obj = new ReturnObj();
        try{
            boolean flag = igSwitchService.changeStatus(igSwitchId, status);
            if(flag){
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setReturnCode(ReturnCode.FAIL);
                obj.setMsg("操作开关失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("操作开关失败！");
        }
        return obj;
    }
}
