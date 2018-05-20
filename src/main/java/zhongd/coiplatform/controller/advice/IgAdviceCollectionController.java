package zhongd.coiplatform.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.DO.advice.IgAdviceCollection;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceCollectionDTO;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.advice.IgAdviceService;
import zhongd.coiplatform.utils.constant.IgAdviceCollectionConstant;
import zhongd.coiplatform.utils.constant.ReturnCode;

/**
 * @Author xiezd
 * @Date 下午 8:42 星期二 2017/12/12 0012
 * @Description
 */
@Controller
@ResponseBody
@RequestMapping("/adviceCollection")
public class IgAdviceCollectionController extends BaseController{

    @Autowired
    IgAdviceService igAdviceService;

    /**
     * 保存或者更新
     * @param igAdviceCollection
     * @return
     */
    @PostMapping("/save")
    public ReturnObj save(@RequestBody IgAdviceCollection igAdviceCollection){
        ReturnObj obj = new ReturnObj();
        try{
            igAdviceCollection.setIgOrgId(getCurrentUser().getIgUserDO().getIgOrgId());
            obj.setData(igAdviceService.save(igAdviceCollection));
            obj.setReturnCode(ReturnCode.SUCCESS);
            obj.setMsg("操作成功！");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("操作失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 根据id删除
     * @param igAdviceCollectionId
     * @return
     */
    @GetMapping("/delete")
    public ReturnObj delete(Integer igAdviceCollectionId){
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igAdviceService.deleteById(igAdviceCollectionId));
            obj.setReturnCode(ReturnCode.SUCCESS);
            obj.setMsg("删除成功！");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("删除失败！");
        }
        return obj;
    }

    /**
     * 获取征集列表、含查询
     * @param dto
     * @param status 状态：1征集中 2已截止（待处理） 3已处理
     * @return
     */
    @PostMapping("/listAll")
    public ReturnObj list(@RequestBody IgAdviceCollectionDTO dto){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igAdviceService.list(dto, getCurrentUser().getIgUserDO().getIgOrgId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("获取列表失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    /**
     * 处理征集结果
     * @param igAdviceCollection
     * @return
     */
    @PostMapping("/handle")
    public ReturnObj handle(@RequestBody IgAdviceCollection igAdviceCollection){
        ReturnObj obj = new ReturnObj();
        try{
            igAdviceCollection.setStatus(IgAdviceCollectionConstant.STATUS_FINISH);
            boolean flag = igAdviceService.save(igAdviceCollection);
            if(flag){
                obj.setReturnCode(ReturnCode.SUCCESS);
                obj.setMsg("处理成功！");
            }else{
                obj.setReturnCode(ReturnCode.SUCCESS);
                obj.setMsg("处理失败，请重新尝试！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("处理征集失败！");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
