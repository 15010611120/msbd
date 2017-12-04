package com.yxd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxd.mapper.ConsumptionRecordMapper;
import com.yxd.model.ConsumptionRecord;
import com.yxd.service.ConsumptionRecordService;

@Service
@Transactional
public class ConsumptionRecordServiceImpl implements ConsumptionRecordService {
	@Autowired
	private ConsumptionRecordMapper consumptionRecordMapper;

	public List<ConsumptionRecord> findAll(Map<String, Object> map) {
		return consumptionRecordMapper.selectAll();
	}

	public int addConsumptionRecord(ConsumptionRecord cRecord) {
		return consumptionRecordMapper.insert(cRecord);
	}

	public List<ConsumptionRecord> findPageList(Map<String, Object> map) {
		return consumptionRecordMapper.findPageList(map);
	}

	public int findCount() {
		return consumptionRecordMapper.findCount();
	}

}
