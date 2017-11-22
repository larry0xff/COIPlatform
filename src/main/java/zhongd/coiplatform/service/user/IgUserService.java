package zhongd.coiplatform.service.user;

import java.util.Set;

import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgRoleDO;
import zhongd.coiplatform.entity.DO.user.IgUser;

public interface IgUserService {

	IgUser getIgUserByUsername(String username);

	ReturnObj login(IgUser user);
	
	Set<IgRoleDO> getUserRoleSet(Integer igUserId);

	int insert(IgUser user);

}