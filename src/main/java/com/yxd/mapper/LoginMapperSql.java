package com.yxd.mapper;

import java.util.Map;

public class LoginMapperSql {

	public String queryLogin(Map<String, Object> map) {
		String userName=(String) map.get("userName");
		String passWord=(String) map.get("passWord");
		String sql="select * from yxd_user where userName='"+userName+"' and passWord='"+passWord+"'";
		return sql;
		
	}
}
