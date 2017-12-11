package com.yxd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxd.mapper.SalarySumMapper;
import com.yxd.model.Salary;
import com.yxd.service.SalarySumService;
@Service
@Transactional
public class SalarySumServiceImpl implements SalarySumService {

	@Autowired
	private SalarySumMapper salarySumMapper;
	@Override
	public int addSalarySum(Salary sa) {
		return salarySumMapper.insert(sa);
	}

	@Override
	public List<Salary> findPageList(Map<String, Object> map) {
		return salarySumMapper.findPageList(map);
	}

	@Override
	public int findCount() {
		return salarySumMapper.findCount();
	}

}
