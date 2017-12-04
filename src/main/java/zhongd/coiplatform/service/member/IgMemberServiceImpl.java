package zhongd.coiplatform.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.member.IgMemberMapper;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;
import zhongd.coiplatform.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:20 星期六 2017/12/2/002
 */
@Service
public class IgMemberServiceImpl implements IgMemberService {
    @Autowired
    IgMemberMapper igMemberMapper;

    @Override
    public Map<String, Object> getMemberList(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(request.getParameter("igMemberId")))
            param.put("igMemberId", Integer.parseInt(request.getParameter("igMemberId")));
        param.put("username", request.getParameter("username"));
        param.put("realname", request.getParameter("realname"));
        param.put("tel", request.getParameter("tel"));
        param.put("email", request.getParameter("email"));
        param.put("page", Integer.parseInt(request.getParameter("page")));
        param.put("pageSize", Integer.parseInt(request.getParameter("pageSize")));
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.getMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }
}
