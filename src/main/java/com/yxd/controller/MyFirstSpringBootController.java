package com.yxd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yxd.model.AuthorizeQPayVo;
import com.yxd.model.User;
import com.yxd.service.LoginService;


@Controller
@RequestMapping("/loginAction")
public class MyFirstSpringBootController {
	
	@Autowired
	private LoginService loginService;
	private static Logger logger = Logger.getLogger(MyFirstSpringBootController.class);
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> springBootLogin(@RequestParam Map<String, Object> mapParam,HttpSession session){
		logger.info("param:"+mapParam);
		Map<String,Object> map= new HashMap<String,Object>();
		 String userName=(String) mapParam.get("userName");
		 //调用servce 查询数据库可用户名与密码是否正确
		 List<User> listLogin = loginService.queryLogin(map);
		 if(listLogin.size()>0) {
			 map.put("rspCode", "00");
			 map.put("rspMsg", "成功");
		 }else {
			 map.put("rspCode", "01");
			 map.put("rspMsg", "失败");
		 }
		 session.setAttribute("userName", userName);
		return map;
	}
	
	@RequestMapping("/logins")
	@ResponseBody
	public ModelAndView springBootLogins(HttpSession session){
		String userName = (String) session.getAttribute("userName");
		ModelAndView mv = new ModelAndView("/index","userName",userName);
		return mv;
		 
	}
	 
    
}
