package zhongd.coiplatform.dao.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import zhongd.coiplatform.entity.DO.user.IgRole;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;

@Mapper
public interface JoinMapper {
	Set<IgRole> getRoleSet(Integer igUserId);

	List<IgUserDTO> getUserList(Map<String, Object> param);
}
