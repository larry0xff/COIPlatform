package zhongd.coiplatform.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;

import zhongd.coiplatform.entity.DTO.user.IgUserLoginDTO;

public class BaseController {
	public IgUserLoginDTO getCurrentUser() {
		IgUserLoginDTO currentUser = (IgUserLoginDTO)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		return currentUser;
	}
	public Logger logger = LogManager.getLogger(getClass());
}
