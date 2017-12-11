package com.yxd.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yxd.model.Salary;
@Service
public interface SalarySumService {

	/**
	 * 添加薪资发放
	 * @param cRecord
	 * @return
	 */
	public int addSalarySum(Salary sa);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<Salary> findPageList(Map<String, Object> map);
	
	/**
	 * 条数
	 * @return
	 */
	public int findCount();

}
