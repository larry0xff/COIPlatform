package zhongd.coiplatform.controller.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.DO.user.IgRole;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.role.IgRoleService;
import zhongd.coiplatform.utils.ReturnCode;

import java.util.Date;
import java.util.Map;

/**
 * @author zdxie
 * @Date 下午 10:17 2017/11/23 0023
 * @Description
 */
@Controller
@RequestMapping(value = "/role")
@ResponseBody
public class IgRoleController extends BaseController {
    @Autowired
    IgRoleService igRoleService;

    /**
     * 新增新角色
     * @param igRole
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ReturnObj insertRole(IgRole igRole){
        ReturnObj obj = new ReturnObj();
        try{
            IgUser currentUser = getCurrentUser().getIgUserDO();
            igRole.setCreateBy(currentUser.getIgUserId());
            igRole.setCreateTime(new Date());
            igRole.setUpdateBy(currentUser.getIgUserId());
            igRole.setUpdateTime(new Date());
            int result = igRoleService.insertRole(igRole);
            if(result == 0){
                obj.setMsg("新增角色失败，请重新尝试！");
                obj.setReturnCode(ReturnCode.FAIL);
            }else{
                obj.setReturnCode(ReturnCode.SUCCESS);
                obj.setMsg("新增角色成功");
            }
        }catch (Exception e){
            logger.error(e);
            obj.setMsg("新增角色失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 删除角色
     * @param igRoleId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnObj insertRole(Integer igRoleId){
        ReturnObj obj = new ReturnObj();
        try{
            int result = igRoleService.deleteRole(igRoleId);
            if(result == 0){
                obj.setMsg("删除角色失败，请重新尝试！");
                obj.setReturnCode(ReturnCode.FAIL);
            }else{
                obj.setReturnCode(ReturnCode.SUCCESS);
                obj.setMsg("删除角色成功");
            }
        }catch (Exception e){
            logger.error(e);
            obj.setMsg("删除角色失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ReturnObj list(){
        ReturnObj obj = new ReturnObj();
        try {
            Map<String, Object> data = igRoleService.list();
            obj.setReturnCode(ReturnCode.SUCCESS);
            obj.setMsg("获取角色列表成功");
            obj.setData(data);
        }catch (Exception e){
            logger.error(e);
            obj.setMsg("获取角色列表失败");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

}
