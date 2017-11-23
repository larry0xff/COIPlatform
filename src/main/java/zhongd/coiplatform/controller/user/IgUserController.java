package zhongd.coiplatform.controller.user;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;
import zhongd.coiplatform.service.user.IgUserService;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.ReturnCode;

@Controller
@RequestMapping(value = "/user")
public class IgUserController extends BaseController{
	@Autowired
	IgUserService igUserService;
	
	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj insert(@Valid IgUser user) {
		ReturnObj returnObj = new ReturnObj();
		try {
			IgUser currentUser = getCurrentUser().getIgUserDO();
			user.setCreateBy(currentUser.getIgUserId());
			user.setUpdateBy(currentUser.getIgUserId());
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			returnObj.setData(igUserService.insert(user));
			returnObj.setReturnCode(ReturnCode.SUCCESS);
			returnObj.setMsg("新增用户成功");
		} catch (Exception e) {
			logger.error(e);
			returnObj.setReturnCode(ReturnCode.FAIL);
			returnObj.setMsg("新增用户失败");
		}
		return returnObj;
		
	}
	
	/**
	 * 根据id删除user
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public ReturnObj delete(@Valid IgUser user) {
		ReturnObj returnObj = new ReturnObj();
		try {
			int result = igUserService.deleteById(user);
			if(result == 0) {
				returnObj.setReturnCode(ReturnCode.FAIL);
				returnObj.setMsg("未更新删除，请尝试重新删除");
			}else {
				returnObj.setData(result);
				returnObj.setReturnCode(ReturnCode.SUCCESS);
				returnObj.setMsg("删除用户成功");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			returnObj.setReturnCode(ReturnCode.FAIL);
			returnObj.setMsg("删除用户失败");
		}
		return returnObj;
		
	}
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj update(@Valid IgUser user) {
		ReturnObj obj = new ReturnObj();
		try {
			user.setUpdateBy(getCurrentUser().getIgUserDO().getIgUserId());
			user.setUpdateTime(new Date());
			int result = igUserService.update(user);
			if(result == 0) {
				obj.setMsg("未更新成功，请尝试重新更新");
				obj.setReturnCode(ReturnCode.FAIL);
			}else {
				obj.setData(result);
				obj.setMsg("更新成功");
				obj.setReturnCode(ReturnCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			obj.setReturnCode(ReturnCode.FAIL);
			obj.setMsg("修改信息用户失败");
		}
		return obj;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ReturnObj list(int pageSize, int pageIndex, IgUserDTO user) {
		ReturnObj obj = new ReturnObj();
		try {
			List<IgUserDTO> data = igUserService.list(pageSize, pageIndex, user);
			obj.setData(data);
			obj.setReturnCode(ReturnCode.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}
		return obj;
	}
}
