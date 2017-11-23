package zhongd.coiplatform.dao.user;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.User;
import zhongd.coiplatform.entity.DO.user.IgUser;

@Mapper
public interface IgUserMapper extends BaseMapper<IgUser>{

	
}
