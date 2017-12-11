package com.yxd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yxd.model.ConsumptionRecord;
import com.yxd.model.Salary;
import com.yxd.service.ConsumptionRecordService;
import com.yxd.service.SalarySumService;
import com.yxd.util.Page;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
public class ConsumprionRecordController {
	
	@Autowired
	private ConsumptionRecordService consumptionRecordService;
	@Autowired
	private SalarySumService salarySumService;

	/**
	 * 消费页面查询跳转
	 * @return
	 */
	@RequestMapping("/consumptionJump")
	public ModelAndView consumptionJump() {
		return new ModelAndView("pages/consumption/consumptionRecord");
	}
	
	@RequestMapping("/addConsumptionJump")
	public ModelAndView addConsumptionJump() {
		return new ModelAndView("pages/consumption/addAndUpdateConsumption");
		
	}
	
	/**
	 * 跳转薪资发放页面
	 * @return
	 */
	@RequestMapping("/salaryJump")
	public ModelAndView querySalarySumList() {
		return new ModelAndView("pages/consumption/querySalarySumList");
		
	}
	
	@RequestMapping("/addAndUpdateSalary")
	public ModelAndView addAndUpdateSalary() {
		return new ModelAndView("pages/consumption/addAndUpdateSalary");
		
	}
	
	/**
	 * 消费记录查询
	 * @param request
	 * @param response
	 * @param mapParam
	 * @return
	 */
	@RequestMapping("/consumptionListQuery")
	@ResponseBody
	public ModelAndView showEdit(HttpServletRequest request,HttpServletResponse response,
	            @RequestParam Map mapParam){
//		将查询条件赋值给page
		Page p=new Page(mapParam);
//		首次加载时 查询做条数
		if(p.isFirst()) {
			int count = consumptionRecordService.findCount();
			p.setItemCount(count);
		}
//		将page对象放入map中
		mapParam.put("p", p);
//		分页sql查询数据 返回list
		List<ConsumptionRecord> tList = consumptionRecordService.findPageList(mapParam);
		request.setAttribute("mapP", mapParam);
		ModelAndView mv = new ModelAndView("pages/consumption/consumptionRecord","list",tList);
		mv.addObject("pages",p.getPageInfo());
		return mv;
	}
	
	/**
	 * 添加消费记录
	 * @param crecord
	 */
	@RequestMapping("/addOrUpdateConsumption")
	public void addOrUpdateConsumption(ConsumptionRecord crecord) {
		Map<String, Boolean> jsonMap = new HashMap<String, Boolean>();
		int i=consumptionRecordService.addConsumptionRecord(crecord);
		if(i==0) {
			jsonMap.put("success", true);
		}else {
			jsonMap.put("error", false);
		}
	}
	
	/**
	 * 薪资发放查询
	 * @param request
	 * @param response
	 * @param mapParam
	 * @return
	 */
	@RequestMapping("/querySalarySumList")
	@ResponseBody
	public ModelAndView querySalarySumList(HttpServletRequest request,HttpServletResponse response,
	            @RequestParam Map mapParam){
//		将查询条件赋值给page
		Page p=new Page(mapParam);
//		首次加载时 查询做条数
		if(p.isFirst()) {
			int count = salarySumService.findCount();
			p.setItemCount(count);
		}
//		将page对象放入map中
		mapParam.put("p", p);
//		分页sql查询数据 返回list
		List<ConsumptionRecord> tList = salarySumService.findPageList(mapParam);
		request.setAttribute("mapP", mapParam);
		ModelAndView mv = new ModelAndView("pages/consumption/querySalarySumList","list",tList);
		mv.addObject("pages",p.getPageInfo());
		return mv;
	}
	
	/**
	 * 添加薪资发放记录
	 * @param crecord
	 */
	@RequestMapping("/addOrUpdate")
	public void addOrUpdate(Salary sa) {
		Map<String, Boolean> jsonMap = new HashMap<String, Boolean>();
		int i=salarySumService.addSalarySum(sa);
		if(i==0) {
			jsonMap.put("success", true);
		}else {
			jsonMap.put("error", false);
		}
	}
}
