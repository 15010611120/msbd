package com.yxd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxd.model.Product;
import com.yxd.model.TerModel;
import com.yxd.model.User;
import com.yxd.service.LoginService;
import com.yxd.util.Page;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/loginAction")
public class MyFirstSpringBootController {
	
	@Autowired
	private LoginService loginService;
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
/*	@RequestMapping("/productListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request,
			 @RequestParam(required=true,defaultValue="1") Integer page,  
	            @RequestParam(required=false,defaultValue="1") Integer pageSize,
	            @RequestParam Map mapParam){
		Object pr=mapParam.get("product");
		 PageHelper.startPage(page, pageSize);
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
		 int count = loginService.queryTercount();
		 request.setAttribute("name", name);
		 PageInfo<Product> p=new PageInfo<Product>(uList); 
		 System.out.println(p.getList());
//		 ModelAndView mv = new ModelAndView("/pages/product/productList","list",uList);
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 Page p1=new Page("/productListQuery");
		 p1.setItemCount(count);
		 p1.setSearchKeys("product", "Qpos");
		 
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("list",uList);
		 mv.addObject("pages",p1.getPageInfo());
		 mv.setViewName("/pages/product/productList");
		 return mv;
		        
	}*/
	
	@RequestMapping("/productListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request,
	            @RequestParam Map mapParam){
		 Object pr=mapParam.get("product");
		 String pageNo=(String) mapParam.get("pageNo");
		 Page p=new Page("/loginAction/productListQuery",mapParam);
		 
		 mapParam.put("pageNm", p.getPageNum());
		 mapParam.put("pageBigSize", p.getPageBigSize());
		 List<TerModel> tList = loginService.queryTerList(mapParam);
		 int count = loginService.queryTercount();

		 Map<String, Object> map = new HashMap<String, Object>();
		 p.setItemCount(count);
		 p.setSearchKeys("product", "Qpos");
		 
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("list",tList);
		 mv.addObject("pages",p.getPageInfo());
		 mv.setViewName("/pages/product/productList");
		 return mv;
		        
	}
	/*@RequestMapping("/productListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request){
		 List<AgTest> listLogin = loginService.queryM();
		 ModelAndView mv = new ModelAndView("/pages/product/productList");
		 return mv;       
	}*/
	
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
