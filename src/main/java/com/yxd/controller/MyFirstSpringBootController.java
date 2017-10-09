package com.yxd.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/loginAction")
public class MyFirstSpringBootController {
	private static Logger logger = Logger.getLogger(MyFirstSpringBootController.class);
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView springBootLogin(@RequestParam Map<String, Object> mapParam){
		logger.info("param:"+mapParam);
		/* Map<String,Object> map= new HashMap<>();
		 String userName=(String) mapParam.get("userName");
		 String passWorld=(String) mapParam.get("password");
		 //调用servce 查询数据库可用户名与密码是否正确
		
		 map.put("rspCode", "00");
		 map.put("rspMsg", "成功");*/
			 
		return new ModelAndView("successLogin");
		 
	}
	 
    
}
