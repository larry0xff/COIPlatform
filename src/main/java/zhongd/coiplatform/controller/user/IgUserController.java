package zhongd.coiplatform.controller.user;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.service.user.IgUserService;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.ReturnCode;

@Controller
@RequestMapping(value = "/user")
public class IgUserController extends BaseController{
	@Autowired
	IgUserService igUserService;
	
	@RequestMapping("/unlogin")
	@ResponseBody
	public ReturnObj unlogin() {
		ReturnObj rtobj= new ReturnObj();
		rtobj.setReturnCode(ReturnCode.NO_AUTORITY);
		return rtobj;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj login(IgUser user) {
		return igUserService.login(user);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj insert(@Valid IgUser user) {
		ReturnObj returnObj = new ReturnObj();
		try {
			IgUser currentUser = getCurrentUser().getIgUserDO();
			user.setCreateBy(currentUser.getIgUserId());
			user.setUpdateBy(currentUser.getIgUserId());
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			returnObj.setData(igUserService.insert(user));
			returnObj.setReturnCode(ReturnCode.SUCCESS);
			returnObj.setMsg("新增用户成功");
		} catch (Exception e) {
			returnObj.setReturnCode(ReturnCode.FAIL);
			returnObj.setMsg("新增用户失败");
		}
		return returnObj;
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj delete(@Valid IgUser user) {
		ReturnObj returnObj = new ReturnObj();
		try {
//			returnObj.setData(igUserService.insert(user));
			returnObj.setReturnCode(ReturnCode.SUCCESS);
			returnObj.setMsg("新增用户成功");
		} catch (Exception e) {
			returnObj.setReturnCode(ReturnCode.FAIL);
			returnObj.setMsg("新增用户失败");
		}
		return returnObj;
		
	}
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj ttt() {
		ReturnObj obj = new ReturnObj();
		obj.setData(getCurrentUser());
		return obj;
	}
}
