package com.yxd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yxd.model.TerModel;
import com.yxd.service.LoginService;
import com.yxd.service.TerModelService;
import com.yxd.util.Page;

@SuppressWarnings({"unused", "unchecked", "rawtypes" })
@Controller
@RequestMapping("/loginAction")
public class MyFirstSpringBootController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private TerModelService terModelService;
	
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
	 

	/**
	 * 分页查询
	 * @param request
	 * @param mapParam
	 * @return
	 */
	@RequestMapping("/productListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request,
	            @RequestParam Map mapParam){
//		将查询条件赋值给page
		Page p=new Page(mapParam);
//		首次加载时 查询做条数
		if(p.isFirstLoad()) {
			int count = loginService.queryTercount();
			p.setItemCount(count);
		}
//		分页sql查询数据 返回list
		mapParam.put("p", p);
		List<TerModel> tList = loginService.queryTerList(mapParam);

		ModelAndView mv = new ModelAndView("/pages/product/productList","list",tList);
		mv.addObject("pages",p.getPageInfo());
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
