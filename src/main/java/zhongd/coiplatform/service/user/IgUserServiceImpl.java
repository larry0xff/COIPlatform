package zhongd.coiplatform.service.user;

import java.util.Date;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.dao.user.IgUserRoleMapper;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgRoleDO;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.IgUserLoginDTO;
import zhongd.coiplatform.utils.Constant;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.ReturnCode;

@Service
public class IgUserServiceImpl implements IgUserService {
	@Autowired
	IgUserMapper igUserMapper;
	@Autowired
	IgUserRoleMapper igUserRoleMapper;
	public IgUser getQueryUserDO() {
		return new IgUser();
	}
	@Override
	public IgUser getIgUserByUsername(String username) {
		IgUser queryUser = getQueryUserDO();
		queryUser.setUsername(username);
		return (IgUser) igUserMapper.selectOne(queryUser);
	}

	@Override
	public ReturnObj login(IgUser user) {
		ReturnObj obj = new ReturnObj();
		if(getIgUserByUsername(user.getUsername()) == null) {
			obj.setMsg("用户不存在");
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
			return obj;
		}
		user.setPassword(PasswordHandler.encodePassword(user.getPassword(), user.getUsername(), Constant.MD5_STR));
		IgUser user0 = (IgUser) igUserMapper.selectOne(user);
		if(user0 == null) {
			obj.setMsg("密码错误");			
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_PASSWORD_INCORRECT);
			return obj;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(user0.getUsername(), user0.getPassword());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		// 将登录信息放进Session中
		IgUserLoginDTO login = new IgUserLoginDTO();
		login.setIgUser(user0);
		login.setLoginTime(new Date());
		subject.getSession().setAttribute("currentUser", login);
		
		obj.setMsg("登录成功");
		obj.setReturnCode(ReturnCode.LOGIN_SUCCESS);
		return obj;
	}
	
	public Set<IgRoleDO> getUserRoleSet(Integer igUserId){
		return igUserRoleMapper.getUserRoleSet(igUserId);
	}
	@Override
	public int insert(IgUser user) {
		user.setPassword(PasswordHandler.encodePassword(user.getPassword(), user.getUsername(), Constant.MD5_STR));
		return igUserMapper.insertSelective(user);
	}
}
