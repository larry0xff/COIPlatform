package zhongd.coiplatform.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.dao.user.IgUserRoleMapper;
import zhongd.coiplatform.dao.user.JoinMapper;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgRole;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;
import zhongd.coiplatform.entity.DTO.user.IgUserLoginDTO;
import zhongd.coiplatform.utils.Constant;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.ReturnCode;

@Service
public class IgUserServiceImpl implements IgUserService {
	@Autowired
	IgUserMapper igUserMapper;
	@Autowired
	JoinMapper joinMapper;
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
		IgUser check = getIgUserByUsername(user.getUsername());
		if(check == null) {
			obj.setMsg("用户不存在");
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
			return obj;
		}
		user.setPassword(PasswordHandler.encodePassword(user.getPassword(), check.getIgUserId(), Constant.MD5_STR));
		if(!user.getPassword().equals(check.getPassword())) {
			obj.setMsg("密码错误");			
			obj.setReturnCode(ReturnCode.LOGIN_ERROR_PASSWORD_INCORRECT);
			return obj;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(check.getUsername(), check.getPassword());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		// 将登录信息放进Session中
		IgUserLoginDTO login = new IgUserLoginDTO();
		login.setIgUser(check);
		login.setLoginTime(new Date());
		subject.getSession().setAttribute("currentUser", login);
		
		obj.setMsg("登录成功");
		obj.setReturnCode(ReturnCode.LOGIN_SUCCESS);
		return obj;
	}
	
	public Map<String, Object> getUserRoleSet(Integer igUserId){
		Set<IgRole> set = joinMapper.getRoleSet(igUserId);
		Map<String, Object> data = new HashMap<>();
		data.put("set", set);
		data.put("count", set.size());
		return data;
	}
	@Override
	public int insert(IgUser user) {
		user.setPassword(PasswordHandler.encodePassword(user.getPassword(), user.getIgUserId(), Constant.MD5_STR));
		return igUserMapper.insertSelective(user);
	}
	@Override
	public int deleteById(IgUser user) {
		return igUserMapper.deleteByPrimaryKey(user.getIgUserId());
	}
	@Override
	public int update(IgUser user) {
		return igUserMapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public List<IgUserDTO> list(int pageSize, int pageIndex, IgUserDTO queryUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		pageIndex -= 1;
		param.put("pageIndex", pageIndex);
		param.put("pageSize", pageSize);
		param.put("condition", queryUser);
		return joinMapper.getUserList(param);
	}

	@Override
	public int setRole(Integer igUserId, Integer igRoleId, Integer currentUserId) {
		Map<String, Object> param = new HashMap<>();
		param.put("igUserId", igUserId);
		param.put("igRoleId", igRoleId);
		param.put("updateBy", currentUserId);
		param.put("createBy", currentUserId);
		param.put("updateTime", new Date());
		param.put("createTime", new Date());
		return joinMapper.setRole(param);
	}

	@Override
	public int rmRole(Integer igUserId, Integer igRoleId) {
		Map<String, Object> param = new HashMap<>();
		param.put("igUserId", igUserId);
		param.put("igRoleId", igRoleId);
		return joinMapper.rmRole(param);
	}
}
