package com.yxd.mapper;

import java.util.Map;

public class LoginMapperSql {

	public String queryLogin(Map<String, Object> map) {
		String userName=(String) map.get("userName");
		String passWorld=(String) map.get("password");
		String sql="select * from user where userName='"+userName+"' and passWorld='"+passWorld+"'";
		return sql;
		
	}
}
