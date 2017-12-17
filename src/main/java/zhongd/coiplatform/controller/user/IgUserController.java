package zhongd.coiplatform.controller.user;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;
import zhongd.coiplatform.service.user.IgUserService;
import zhongd.coiplatform.utils.constant.Constant;
import zhongd.coiplatform.utils.PasswordHandler;
import zhongd.coiplatform.utils.constant.ReturnCode;
import zhongd.coiplatform.utils.StringUtil;

@Controller
@RequestMapping(value = "/user")
@ResponseBody
public class IgUserController extends BaseController{
	@Autowired
	IgUserService igUserService;
	
	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ReturnObj insert(@RequestBody IgUser user) {
		ReturnObj returnObj = new ReturnObj();
		try {
			if (igUserService.getIgUserByUsername(user.getUsername()) != null) {
				returnObj.setReturnCode(ReturnCode.FAIL);
				returnObj.setMsg("用户名已存在，请更换用户名！");
			}else{
				IgUser currentUser = getCurrentUser().getIgUserDO();
				user.setCreateBy(currentUser.getIgUserId());
				user.setUpdateBy(currentUser.getIgUserId());
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				user.setPassword("123456");
				returnObj.setData(igUserService.insert(user));
				returnObj.setReturnCode(ReturnCode.SUCCESS);
				returnObj.setMsg("新增用户成功");
			}
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
	public ReturnObj update(@RequestBody IgUser user) {
		ReturnObj obj = new ReturnObj();
		try {
			IgUser currentUser = getCurrentUser().getIgUserDO();
			user.setUpdateBy(currentUser.getIgUserId());
			user.setUpdateTime(new Date());
			if(!StringUtil.isEmpty(user.getPassword())){
				user.setPassword(PasswordHandler.encodePassword(user.getPassword(),user.getUsername(), Constant.MD5_STR));
			}
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
	@PostMapping(value = "/updatePsw")
	public ReturnObj updatePsw(@RequestParam("oldPsw") String oldPsw, @RequestParam("newPsw") String newPsw){
		ReturnObj obj = new ReturnObj();
		try {
			IgUser currentUser = getCurrentUser().getIgUserDO();
			if(!PasswordHandler.encodePassword(oldPsw, currentUser.getUsername(), Constant.MD5_STR).equals(currentUser.getPassword())){
				obj.setMsg("旧密码错误！");
				obj.setReturnCode(ReturnCode.FAIL);
			}else{
				currentUser.setPassword(PasswordHandler.encodePassword(newPsw, currentUser.getUsername(), Constant.MD5_STR));
				int result = igUserService.update(currentUser);
				if(result == 0) {
					obj.setMsg("未修改成功，请尝试重新更新");
					obj.setReturnCode(ReturnCode.FAIL);
				}else {
					obj.setData(result);
					obj.setMsg("修改成功");
					obj.setReturnCode(ReturnCode.SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			obj.setReturnCode(ReturnCode.FAIL);
			obj.setMsg("修改密码失败");
		}
		return obj;
	}
	/**
	 * 分页获取用户列表
	 * @param pageSize
	 * @param pageIndex
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
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

	/**
	 * 为用户设置角色
	 * @param igUserId
	 * @param igRoleId
	 * @return
	 */
	@RequestMapping(value = "/setRole", method = RequestMethod.POST)
	public ReturnObj setRole(Integer igUserId, Integer igRoleId){
		ReturnObj obj = new ReturnObj();
		try{
			int result = igUserService.setRole(igUserId, igRoleId, getCurrentUser().getIgUserDO().getIgUserId());
			obj.setData(result);
			if(result == 0){
				obj.setReturnCode(ReturnCode.FAIL);
				obj.setMsg("设置角色失败，请尝试重新设置");
			}else{
				obj.setReturnCode(ReturnCode.SUCCESS);
				obj.setMsg("设置角色成功");
			}
		}catch (Exception e){
			logger.error(e);
			obj.setMsg("设置角色失败！");
			obj.setReturnCode(ReturnCode.FAIL);
		}
		return obj;
	}

	/**
	 * 为用户取消角色
	 * @param igUserId
	 * @param igRoleId
	 * @return
	 */
	@RequestMapping(value = "/rmRole", method = RequestMethod.POST)
	public ReturnObj rmRole(@RequestParam("igUserId") Integer igUserId, @RequestParam("igRoleId") Integer igRoleId){
		ReturnObj obj = new ReturnObj();
		try{
			int result = igUserService.rmRole(igUserId, igRoleId);
			obj.setData(result);
			if(result == 0){
				obj.setReturnCode(ReturnCode.FAIL);
				obj.setMsg("移除角色失败，请尝试重新设置");
			}else{
				obj.setReturnCode(ReturnCode.SUCCESS);
				obj.setMsg("移除角色成功");
			}
		}catch (Exception e){
			logger.error(e);
			obj.setMsg("移除角色失败！");
			obj.setReturnCode(ReturnCode.FAIL);
		}
		return obj;
	}

	/**
	 * 获取用户角色列表
	 * @param igUserId
	 * @return
	 */
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ReturnObj roles(Integer igUserId){
		ReturnObj obj = new ReturnObj();
		try{
			obj.setData(igUserService.getUserRoleSet(igUserId));
			obj.setReturnCode(ReturnCode.SUCCESS);
			obj.setMsg("获取用户角色成功");
		}catch (Exception e){
			logger.error(e);
			obj.setMsg("获取用户角色失败");
			obj.setReturnCode(ReturnCode.FAIL);
		}
		return obj;
	}

	@GetMapping(value = "/rolesSelect")
	public ReturnObj rolesSelect(Integer igUserId){
		ReturnObj obj = new ReturnObj();
		try{
			obj.setData(igUserService.getUserRoleSelectSet(igUserId));
			obj.setReturnCode(ReturnCode.SUCCESS);
			obj.setMsg("获取用户角色成功");
		}catch (Exception e){
			logger.error(e);
			obj.setMsg("获取用户角色失败");
			obj.setReturnCode(ReturnCode.FAIL);
		}
		return obj;
	}
}
