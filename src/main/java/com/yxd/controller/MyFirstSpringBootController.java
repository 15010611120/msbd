package com.yxd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yxd.model.Product;
import com.yxd.model.User;
import com.yxd.service.LoginService;


@Controller
@RequestMapping("/loginAction")
public class MyFirstSpringBootController {
	
	@SuppressWarnings("unused")
	@Autowired
	private LoginService loginService;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(MyFirstSpringBootController.class);
	
	@RequestMapping("/userListJump")
	@ResponseBody
	public ModelAndView springBootLoginjump(HttpSession session){
		ModelAndView mv = new ModelAndView("/pages/userList");
		return mv;
		 
	}
	
	@RequestMapping("/productListJump")
	@ResponseBody
	public ModelAndView springBootProductjump(HttpSession session){
		ModelAndView mv = new ModelAndView("/pages/product/productList");
		return mv;
		 
	}
	 
	
/*	 public ModelAndView moveByAgentNum(String agentNums){
		 logger.info("param:"+agentNums);
		 User u = new User();
		 u.setUserName("杨晓东");
		 u.setPassword("123456");
		 List<User> uList= new ArrayList<User>();
		 uList.add(u);
		return new ModelAndView("/pages/productList","list",uList);
		
	}*/
	@RequestMapping("/productListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request){
		 Date now = new Date(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		 String myD = dateFormat.format( now ); 
		 List<Product> uList= new ArrayList<Product>();
		 
		 Product u = new Product();
		 u.setProductName("mPos");
		 u.setProductType("mPOS3.2.3");
		 u.setRemarks("海科融通");
		 u.setOperator("杨晓东");
		 u.setAddTime(myD);
		 uList.add(u);
		 
		 Product u1 = new Product();
		 u1.setProductName("QPOS");
		 u1.setProductType("QPOS3.2.3");
		 u1.setRemarks("海科融通");
		 u1.setOperator("杨晓东");
		 u1.setAddTime(myD);
		 uList.add(u1);
		 
		 Product u2 = new Product();
		 u2.setProductName("QPOS");
		 u2.setProductType("QPOS3.2.3");
		 u2.setRemarks("海科融通");
		 u2.setOperator("杨晓东");
		 u2.setAddTime(myD);
		 uList.add(u2);
		 String name="茶品";
		 request.setAttribute("name", name);
		 ModelAndView mv = new ModelAndView("/pages/product/productList","list",uList);
		 return mv;       
	}
	
	/**
	 * 添加产品
	 * @return
	 */
	@RequestMapping("/productAdd")
	@ResponseBody
	public ModelAndView productAdd() {
		 return new ModelAndView("/pages/product/productAdd");
	}
	
	/**
	 * 计算器
	 * @return
	 */
	@RequestMapping("/calculator")
	@ResponseBody
	public ModelAndView calculator() {
		 return new ModelAndView("/pages/calculator/calculator");
	}
    
}
