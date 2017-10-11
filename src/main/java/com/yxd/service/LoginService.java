package com.yxd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxd.mapper.LoginMapper;
import com.yxd.model.AuthorizeQPayVo;
import com.yxd.model.User;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	/**
	 * 查询用户名密码
	 * @param map
	 * @return
	 */
	public List<User> queryLogin(Map<String, Object> map) {
		return loginMapper.queryLogin(map);
	}
	
}
