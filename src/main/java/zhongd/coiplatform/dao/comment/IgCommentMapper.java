package zhongd.coiplatform.dao.comment;

import org.apache.ibatis.annotations.Mapper;
import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.comment.IgComment;
import zhongd.coiplatform.entity.DTO.comment.IgCommentDTO;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-01-06 10:44
 */
@Mapper
public interface IgCommentMapper extends BaseMapper<IgComment>{
    List<IgCommentDTO> list(Map<String, Object> paramMap);
}
