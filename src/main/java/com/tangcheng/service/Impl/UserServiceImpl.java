package com.tangcheng.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tangcheng.dao.UserMapper;
import com.tangcheng.pojo.User;
import com.tangcheng.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper um;
	@Override
	@Cacheable(value="cacheTest", key="#userid")
	public String get(String userid) {
		User user = um.SelectUserById(userid);
		String name = user.getName();
		return name;
	}

}
