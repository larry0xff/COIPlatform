package zhongd.coiplatform.service.member;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:15 星期六 2017/12/2/002
 */
public interface IgMemberService {
    Map<String, Object> getMemberList(HttpServletRequest request);
}
