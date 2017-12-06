package zhongd.coiplatform.dao.member;

import org.apache.ibatis.annotations.Mapper;
import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DO.user.IgRole;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface IgMemberMapper extends BaseMapper<IgMember>{
    List<IgMemberDTO> getMemberList(Map<String, Object> param);

    List<IgMemberDTO> searchMemberList(Map<String, Object> param);
}
