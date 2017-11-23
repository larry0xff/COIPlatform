package zhongd.coiplatform.service.user;

import java.util.List;
import java.util.Set;

import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgRole;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;

public interface IgUserService {

	/**
	 * 根据用户名找user
	 * @param username
	 * @return
	 */
	IgUser getIgUserByUsername(String username);

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	ReturnObj login(IgUser user);
	
	/**
	 * 得到用户角色集合
	 * @param igUserId
	 * @return
	 */
	Set<IgRole> getUserRoleSet(Integer igUserId);

	/**
	 * 插入一条用户记录
	 * @param user
	 * @return
	 */
	int insert(IgUser user);

	/**
	 * 根据主键删除记录
	 * @param user
	 * @return
	 */
	int deleteById(IgUser user);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int update(IgUser user);

	List<IgUserDTO> list(int pageSize, int pageIndex, IgUserDTO queryUser);

}