package zhongd.coiplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.dao.UserMapper;
import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.entity.User;
import zhongd.coiplatform.entity.DO.user.IgUserDO;

@Controller
public class TestController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	IgUserMapper igUserMapper;
	
	
	@RequestMapping(value = "aa",method = RequestMethod.GET)
	@ResponseBody
	public User aa(String username) {
		User u = userMapper.getObj(username);
		return u;
	}
	
	@RequestMapping(value = "bb",method = RequestMethod.GET)
	@ResponseBody
	public IgUserDO bb(String username) {
		IgUserDO u = igUserMapper.getObj(username);
		return u;
	}
}
