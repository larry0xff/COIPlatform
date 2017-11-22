package zhongd.coiplatform.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgUserDO;
import zhongd.coiplatform.service.user.IgUserService;
import zhongd.coiplatform.utils.ReturnCode;

@Controller
@RequestMapping(value = "/user")
public class IgUserController {
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
	public ReturnObj login(IgUserDO user) {
		return igUserService.login(user);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj ttt() {
		
		return null;
	}
	public ReturnObj insertUser() {
		ReturnObj returnObject = new ReturnObj();
		
		return returnObject;
		
	}
}
