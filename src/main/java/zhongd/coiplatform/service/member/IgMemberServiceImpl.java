package zhongd.coiplatform.service.member;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.member.IgMemberMapper;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;
import zhongd.coiplatform.entity.DTO.user.IgUserLoginDTO;
import zhongd.coiplatform.utils.Constant;
import zhongd.coiplatform.utils.ConvertTools;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
        String condition = request.getParameter("condition")==null?"":request.getParameter("condition");
        if(StringUtil.isNum(condition))
            param.put("igMemberId", Integer.parseInt(condition));
        param.put("username", "%" + condition + "%");
        param.put("realname", "%" + condition + "%");
        param.put("tel", "%" + condition + "%");
        param.put("email", "%" + condition + "%");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        page = (page - 1) * pageSize;
        param.put("page", page);
        param.put("pageSize", pageSize);
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.getMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    public int deleteById(int igMemberId){
        return igMemberMapper.deleteByPrimaryKey(igMemberId);
    }

    public int insert(IgMemberDTO dto){
        IgMember igMember = ConvertTools.convert(IgMember.class, dto);
        igMember.setCreateBy(((IgUserLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getIgUserDO().getIgUserId());
        igMember.setCreateTime(new Date());
        igMember.setPassword(PasswordHandler.encodePassword("123456", igMember.getUsername(), Constant.MD5_STR));
        return igMemberMapper.insertSelective(igMember);
    }

    @Override
    public int resetPassword(Integer igMemberId, String username) {
        IgMember member = new IgMember();
        member.setIgMemberId(igMemberId);
        member.setPassword(PasswordHandler.encodePassword("123456", username, Constant.MD5_STR));
        return igMemberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Map<String, Object> searchMemberList(String condition) {
        Map<String, Object> param = new HashMap<>();
        if(StringUtil.isNum(condition))
            param.put("idcondition", Integer.parseInt(condition));
        param.put("condition", "%" + condition + "%");
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.searchMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }
}
