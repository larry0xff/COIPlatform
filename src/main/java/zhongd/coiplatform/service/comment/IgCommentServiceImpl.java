package zhongd.coiplatform.service.comment;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.comment.IgCommentMapper;
import zhongd.coiplatform.entity.DO.comment.IgComment;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DTO.comment.IgCommentDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-01-06 10:26
 */
@Service
public class IgCommentServiceImpl implements IgCommentService {
    @Autowired
    IgCommentMapper igCommentMapper;
    @Override
    public List<IgCommentDTO> list(int page, int pageSize, Integer igMemberId) {
        Map<String, Object> paramMap = new HashMap<>();
        page = (page - 1) * pageSize;
        paramMap.put("page", page);
        paramMap.put("pageSize", pageSize);
        paramMap.put("igMemberId", igMemberId);
        return igCommentMapper.list(paramMap);
    }

    @Override
    public boolean save(IgComment comment, Integer currentMemberId) {
        comment.setCreateTime(new Date());
        comment.setCreateBy(currentMemberId);
        if(comment.getReplyId() != null && comment.getReplyId() != 0){
            comment.setIsRead("N");
        }
        boolean flag = igCommentMapper.insertSelective(comment) > 0;
        return flag;
    }

    @Override
    public boolean agree(Integer igCommentId, String flag) {
        IgComment comment = igCommentMapper.selectByPrimaryKey(igCommentId);
        if(flag.equals("Y")){
            comment.setAgree(comment.getAgree() + 1);
        }else{
            comment.setAgainst(comment.getAgainst() + 1);
        }
        return igCommentMapper.updateByPrimaryKeySelective(comment) > 0;
    }
}
