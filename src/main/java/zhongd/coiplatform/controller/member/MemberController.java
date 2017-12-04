package zhongd.coiplatform.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.member.IgMemberService;
import zhongd.coiplatform.utils.ReturnCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  14:41 星期六 2017/12/2/002
 */
@Controller
@RequestMapping(value = "/member")
@ResponseBody
public class MemberController extends BaseController {
    @Autowired
    IgMemberService igMemberService;

    /**
     * 获取成员列表
     * @param request
     * @return
     */
    @PostMapping(value = "/list")
    public ReturnObj list(HttpServletRequest request){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMemberService.getMemberList(request));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取列表出错");
        }
        return obj;
    }
}
