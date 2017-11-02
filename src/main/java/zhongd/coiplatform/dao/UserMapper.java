package zhongd.coiplatform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zhongd.coiplatform.entity.User;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM USER WHERE USER.username = #{username}")
	public User getObj(@Param(value = "username") String username);
}
