package com.yxd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import com.yxd.model.ConsumptionRecord;

import tk.mybatis.mapper.common.Mapper;
@Component
public interface ConsumptionRecordMapper extends Mapper<ConsumptionRecord> {

	@SelectProvider(type = ConsumptionRecordMapperSql.class, method = "findConsumptionRecordPageList")
	public List<ConsumptionRecord> findPageList(Map<String, Object> map);
	
	@SelectProvider(type = ConsumptionRecordMapperSql.class, method = "queryConsumptionRecordTercount")
	public int findCount();
}
