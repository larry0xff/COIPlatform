package zhongd.coiplatform.controller.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.DO.org.IgOrg;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.org.IgOrgService;
import zhongd.coiplatform.utils.ReturnCode;

import java.util.Date;

/**
 * @Author xiezd
 * @Date 下午 9:49 星期六 2017/12/2 0002
 * @Description
 */
@Controller
@RequestMapping(value = "/org")
@ResponseBody
public class IgOrgController extends BaseController{

    @Autowired
    IgOrgService igOrgService;

    /**
     * 获取组织列表
     * @return
     */
    @GetMapping(value = "/list")
    public ReturnObj list(){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igOrgService.getOrgList());
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取列表失败！");
        }
        return obj;
    }

    /**
     * 更新组织信息
     * @param igOrg
     * @return
     */
    @PostMapping(value = "/update")
    public ReturnObj update(@RequestBody IgOrg igOrg){
        ReturnObj obj = new ReturnObj();
        try{
            if(igOrg.getIgOrgId() == null || !(igOrg.getIgOrgId() > 0)){
                obj.setMsg("参数有误！没收到id呀");
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
            }else{
                igOrg.setUpdateBy(getCurrentUser().getIgUserDO().getIgUserId());
                igOrg.setUpdateTime(new Date());
                obj.setData(igOrgService.updateOrg(igOrg));
                obj.setReturnCode(ReturnCode.SUCCESS);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            obj.setMsg("更新失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 根据ID删除组织
     * @param igOrgId
     * @return
     */
    @GetMapping(value = "/delete")
    public ReturnObj delete(Integer igOrgId){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igOrgService.deleteOrgById(igOrgId));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("删除失败");
        }
        return obj;
    }

    /**
     * 新增一个组织
     * @param igOrg
     * @return
     */
    @PostMapping(value = "/save")
    public ReturnObj save(@RequestBody IgOrg igOrg){
        ReturnObj obj = new ReturnObj();
        try{
            igOrg.setCreateBy(getCurrentUser().getIgUserDO().getIgUserId());
            igOrg.setCreateTime(new Date());
            obj.setData(igOrgService.saveOrg(igOrg));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("新增失败");
        }
        return obj;
    }
}
