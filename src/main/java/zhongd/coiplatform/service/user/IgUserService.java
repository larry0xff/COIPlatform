package zhongd.coiplatform.service.user;

import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.IgUserDO;

public interface IgUserService {

	IgUserDO getIgUserByUsername(String username);

	ReturnObj login(IgUserDO user);

}