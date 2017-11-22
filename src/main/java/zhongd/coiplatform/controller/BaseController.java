package zhongd.coiplatform.controller;

import org.apache.shiro.SecurityUtils;

import zhongd.coiplatform.entity.DTO.IgUserLoginDTO;

public class BaseController {
	public IgUserLoginDTO getCurrentUser() {
		IgUserLoginDTO currentUser = (IgUserLoginDTO)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		return currentUser;
	}
}
