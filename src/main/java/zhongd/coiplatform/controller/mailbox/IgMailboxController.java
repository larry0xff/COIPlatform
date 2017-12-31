package zhongd.coiplatform.controller.mailbox;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.mailbox.IgMailboxService;
import zhongd.coiplatform.utils.constant.ReturnCode;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:21
 * @Description
 */
@RequestMapping("/mailbox")
@RestController
public class IgMailboxController extends BaseController{
    @Autowired
    private IgMailboxService igMailboxService;

    /**
     * 获取当前用户所在部门的所有信件
     * @return
     */
    @GetMapping("/list")
    public ReturnObj getList(String status){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igMailboxService.list(getCurrentUser().getIgUserDO().getIgOrgId(), status));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("获取列表失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 设置信件读取状态
     * @param igMailId 信件id
     * @param isRead 读取状态 Y N
     * @return
     */
    @GetMapping("/read")
    public ReturnObj read(Integer igMailId, String isRead){
        ReturnObj obj= new ReturnObj();
        try{
            boolean result = igMailboxService.changeReadStatus(igMailId, isRead);
            obj.setData(result);
            if(result){
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setReturnCode(ReturnCode.FAIL);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
    /**
     * 回复信件
     * @param reply 回复内容
     * @param igMailId 信件id
     * @return
     */
    @PostMapping("/reply")
    public ReturnObj reply(String reply, Integer igMailId){
        ReturnObj obj = new ReturnObj();
        try{
            boolean result = igMailboxService.reply(reply, igMailId, getCurrentUser().getIgUserDO().getIgUserId());
            obj.setData(result);
            if(result){
                obj.setMsg("回复成功！");
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setMsg("回复失败！");
                obj.setReturnCode(ReturnCode.FAIL);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("回复失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
