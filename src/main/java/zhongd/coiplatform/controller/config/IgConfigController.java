package zhongd.coiplatform.controller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.config.IgConfigService;
import zhongd.coiplatform.utils.constant.ReturnCode;

import java.util.Map;

/**
 * @author xiezd 2018-05-17 14:02
 */
@RestController
@RequestMapping("/config")
public class IgConfigController extends BaseController{

    @Autowired
    private IgConfigService igConfigService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public ReturnObj list() {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igConfigService.list());
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取数据失败！");
        }
        return obj;
    }

    @PostMapping("/update")
    public ReturnObj update(@RequestBody Map<String, String> param, @RequestParam Integer igConfigId) {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igConfigService.update(param, igConfigId, getCurrentUser().getIgUserDO().getIgUserId()));
            obj.setMsg("修改成功！");
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("操作失败！");
        }
        return obj;
    }

    @PostMapping("/save")
    public ReturnObj save(@RequestBody Map<String, String> param) {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igConfigService.saveConfig(param, getCurrentUser().getIgUserDO().getIgUserId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("保存失败");
        }
        return obj;
    }

    @GetMapping("/delete")
    public ReturnObj delete(@RequestParam Integer igConfigId) {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igConfigService.delete(igConfigId));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("删除失败");
        }
        return obj;
    }
}
