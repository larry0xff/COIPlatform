package zhongd.coiplatform.service.user;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.IgUserDO;
import zhongd.coiplatform.entity.DTO.IgUserLoginDTO;
import zhongd.coiplatform.utils.ReturnCode;

@Service
public class IgUserServiceImpl implements IgUserService {
	@Autowired
	IgUserMapper igUserMapper;
	
	@Override
	public IgUserDO getIgUserByUsername(String username) {
		return igUserMapper.getObj(username);
	}

	@Override
	public ReturnObj login(IgUserDO user) {
		ReturnObj obj = new ReturnObj();
		if(getIgUserByUsername(user.getUsername()) == null) {
			obj.setMsg("用户不存在");
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
			return obj;
		}
		IgUserDO user0 = igUserMapper.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
		if(user0 == null) {
			obj.setMsg("密码错误");
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_PASSWORD_INCORRECT);
			return obj;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(user0.getUsername(), user0.getPassword());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		
		IgUserLoginDTO login = new IgUserLoginDTO();
		login.setIgUserDO(user0);
		login.setLoginTime(new Date());
		subject.getSession().setAttribute("currentUser", login);
		
		obj.setMsg("登录成功");
		obj.setReturnCode(ReturnCode.LOGIN_SUCCESS);
		return obj;
	}
}
