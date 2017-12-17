package zhongd.coiplatform.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.service.advice.IgAdviceService;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  20:03 星期六 2017/12/16/016
 */
@Controller
@ResponseBody
@RequestMapping("/advice")
public class IgAdviceController extends BaseController{
    @Autowired
    private IgAdviceService igAdviceService;

}
