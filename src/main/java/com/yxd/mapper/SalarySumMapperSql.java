package com.yxd.mapper;

import java.util.Map;

import com.yxd.util.Page;
import com.yxd.util.PageSqlUtil;

public class SalarySumMapperSql {

	public String findSalarySumPageList(Map<String, Object> map) {
//		获取page对象
		Page p = (Page) map.get("p");
		String sql = "select * from yxd_SalarySum";
//		oracle通用分页sql
		String sqls = PageSqlUtil.pageSqlUtil(sql, p);
		return sqls;
	}
	public String querySalarySumRcount() {
		String sql="select count(cr.id) from yxd_SalarySum cr" ;
		return sql;
	}
}
