package zhongd.coiplatform.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.entity.DO.IgUserDO;
import zhongd.coiplatform.entity.DTO.IgUserDTO;

@Service
public class IgUserServiceImpl implements IgUserService {
	@Autowired
	IgUserMapper igUserMapper;
	
	@Override
	public IgUserDO getIgUserByUsername(String username) {
		return igUserMapper.getObj(username);
	}
}
