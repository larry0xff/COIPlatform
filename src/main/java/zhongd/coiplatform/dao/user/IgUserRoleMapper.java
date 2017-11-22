package zhongd.coiplatform.dao.user;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import zhongd.coiplatform.entity.DO.user.IgRoleDO;

@Mapper
public interface IgUserRoleMapper {
	@Select(value = 
			"SELECT r.* FROM ig_user_role ur " + 
			"LEFT JOIN ig_role r on ur.ig_role_id = r.ig_role_id " + 
			"WHERE ur.ig_user_id = #{arg1}")
	public Set<IgRoleDO> getUserRoleSet(Integer igUserId);
}
