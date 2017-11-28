package zhongd.coiplatform.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.service.user.IgUserService;
import zhongd.coiplatform.utils.ReturnCode;

@Controller
public class PermissionController extends BaseController{
	@Autowired
	IgUserService igUserService;
	
	@RequestMapping(value = {"/logout", "/"})
	public String unlogin() {
		return "redirect:/page/login/login.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj login(@RequestBody IgUser user) {
		return igUserService.login(user);
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	@ResponseBody
	public ReturnObj denied() {
		ReturnObj rtobj= new ReturnObj();
		rtobj.setReturnCode(ReturnCode.NO_PERMISSION);
		rtobj.setMsg("没有权限进行此操作");
		return rtobj;
	}
	
	
}
