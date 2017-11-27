package com.yxd.util;

public class PageSqlUtil {
	
	/**
	 * oracle 分页sql
	 * @param sql-->sql
	 * @param p-->分页对象
	 * @return
	 */
	public static String pageSqlUtil(String sql,Page p) {
		return sql="select *  from (select t.*, rownum RN from ("+ sql+") t where rownum <= '"+p.getpSize()+"' ) where rn >='"+p.getpNo()+"'";
	}
	

}
