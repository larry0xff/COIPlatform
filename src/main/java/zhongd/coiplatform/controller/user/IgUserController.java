package zhongd.coiplatform.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zhongd.coiplatform.entity.ReturnObject;
import zhongd.coiplatform.service.user.IgUserService;

@Controller
@RequestMapping(value = "/user")
public class IgUserController {
	@Autowired
	IgUserService igUserService;
	
	public ReturnObject insertUser() {
		ReturnObject returnObject = new ReturnObject();
		
		return returnObject;
		
	}
}
