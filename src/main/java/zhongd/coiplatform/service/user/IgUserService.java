package zhongd.coiplatform.service.user;

import java.util.Set;

import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgRoleDO;
import zhongd.coiplatform.entity.DO.user.IgUserDO;

public interface IgUserService {

	IgUserDO getIgUserByUsername(String username);

	ReturnObj login(IgUserDO user);
	
	Set<IgRoleDO> getUserRoleSet(Integer igUserId);

}