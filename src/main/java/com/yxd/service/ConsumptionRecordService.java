package com.yxd.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxd.model.ConsumptionRecord;
@Service
@Transactional
public interface ConsumptionRecordService {
	
	/**
	 * 消费记录分页查询
	 * @param map
	 * @return
	 */
	public List<ConsumptionRecord> findAll(Map<String, Object> map);
	
	/**
	 * 添加消费记录
	 * @param cRecord
	 * @return
	 */
	public int addConsumptionRecord(ConsumptionRecord cRecord);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<ConsumptionRecord> findPageList(Map<String, Object> map);
	
	/**
	 * 条数
	 * @return
	 */
	public int findCount();

}
