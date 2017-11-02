package zhongd.coiplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zhongd.coiplatform.dao.UserMapper;
import zhongd.coiplatform.entity.User;

@Controller
public class TestController {
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value="aa")
	@ResponseBody
	public User aa() {
		User u = userMapper.getObj("tim");
		return u;
	}
}
