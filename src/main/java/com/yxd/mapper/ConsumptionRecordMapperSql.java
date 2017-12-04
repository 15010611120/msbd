package com.yxd.mapper;

import java.util.Map;

import com.yxd.util.Page;
import com.yxd.util.PageSqlUtil;

public class ConsumptionRecordMapperSql {

	public String findConsumptionRecordPageList(Map<String, Object> map) {
//		获取page对象
		Page p = (Page) map.get("p");
		String sql = "select * from yxd_consumption_record";
//		oracle通用分页sql
		String sqls = PageSqlUtil.pageSqlUtil(sql, p);
		return sqls;
	}
	public String queryConsumptionRecordTercount() {
		String sql="select count(cr.id) from yxd_consumption_record cr" ;
		return sql;
	}
}
