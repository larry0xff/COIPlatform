package zhongd.coiplatform.service.comment;

import zhongd.coiplatform.entity.DO.comment.IgComment;
import zhongd.coiplatform.entity.DTO.comment.IgCommentDTO;

import java.util.List;

/**
 * @author xiezd 2018-01-06 10:25
 */
public interface IgCommentService {
    List<IgCommentDTO> list(int page, int pageSize, Integer igMemberId);

    boolean save(IgComment comment, Integer currentMemberId);

    boolean agree(Integer igCommentId, String flag);
}
