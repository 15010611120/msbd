package com.yxd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import com.yxd.model.AuthorizeQPayVo;
import com.yxd.model.User;

@Component
public interface LoginMapper {
/*
	@Select("select * from "表名" where id=#{id} and agentNum like #{agentNum}")
	public “实体” searchAgentByIdAndName(@Param("id")int id,@Param("name")String names);
	*/
	
	@SelectProvider(type = LoginMapperSql.class, method = "queryLogin")
	public List<User> queryLogin(Map<String, Object> map);
	
	
}
