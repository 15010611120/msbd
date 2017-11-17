package com.yxd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
@Controller
public class SpringBootLoginController {
//	打印log到控制台
	private static Logger logger = Logger.getLogger(MyFirstSpringBootController.class);

	/**
	 * 校验用户名密码
	 * @param mapParam
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginChecked")
	@ResponseBody
	public Map<String,Object> springBootLogin(@RequestParam Map<String, Object> mapParam,HttpSession session){
		logger.info("param:"+mapParam);
		Map<String,Object> map= new HashMap<String,Object>();
		 String email=(String) mapParam.get("email");
		 String userPwd=(String) mapParam.get("userPwd");
		 if("1099025603@qq.com".equals(email)){
			 if("123456".equals(userPwd)) {
				 map.put("rspCode", "00");
				 map.put("rspMsg", "成功");
			 }else {
				 map.put("rspCode", "01");
				 map.put("rspMsg", "密码错误");
			 }
		 }else {
			 map.put("rspCode", "01");
			 map.put("rspMsg", "用户名错误");
		 }
		 /*//调用servce 查询数据库可用户名与密码是否正确
		 List<User> listLogin = loginService.queryLogin(map);
		 if(listLogin.size()>0) {
			 map.put("rspCode", "00");
			 map.put("rspMsg", "成功");
		 }else {
			 map.put("rspCode", "01");
			 map.put("rspMsg", "失败");
		 }*/
		 session.setAttribute("email", email);
		 return map;
	}
	
	/**
	 * 跳转登陆页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView login(HttpSession session){
		return new ModelAndView("pages/login/loginIndex");
	}
	
	/**
	 * 跳转后台页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginIndex")
	@ResponseBody
	public ModelAndView loginIndex(HttpSession session){
		return new ModelAndView("/indexFirst");
	}
	
	/**
	 * 注册页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public ModelAndView register(HttpSession session){
		return new ModelAndView("/pageslogin/register");
	}

}
