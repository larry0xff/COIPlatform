package zhongd.coiplatform.controller.member;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;
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
public class IgMemberController extends BaseController {
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
    /**
     * 获取成员列表
     * @param request
     * @return
     */
    @PostMapping(value = "/search")
    public ReturnObj search(@RequestBody String condition){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMemberService.searchMemberList(condition));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("查询出错!");
        }
        return obj;
    }
    /**
     * 根据id删除单个成员
     * @param igMemberId
     * @return
     */
    @GetMapping(value = "/delete")
    public ReturnObj delete(Integer igMemberId){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMemberService.deleteById(igMemberId));
            obj.setMsg("删除成功！");
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("删除失败！");
        }
        return obj;
    }

    /**
     * 插入一条成员记录
     * @param dto
     * @return
     */
    @PostMapping(value = "/insert")
    public ReturnObj insert(@RequestBody IgMemberDTO dto){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMemberService.insert(dto));
            obj.setMsg("插入成功！");
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("插入成员记录失败！");
        }
        return obj;
    }

    /**
     * 重置密码为123456
     * @param igMemberId id
     * @param username 用户名
     * @return
     */
    @GetMapping(value = "/resetPassword")
    public ReturnObj resetPassword(Integer igMemberId, String username){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMemberService.resetPassword(igMemberId, username));
            obj.setMsg("重置密码成功！");
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("重置密码失败！");
        }
        return obj;
    }
}
