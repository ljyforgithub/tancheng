package com.tangcheng.dao;

import java.util.List;

import com.tangcheng.pojo.User;
import com.tangcheng.pojo.UserCollect;

public interface UserMapper {
	
	public List<User> SelectAllUser();

	public User SelectUserById(String id);

	public int insertUser(User user);

	public int updateUser(User user);
	
	public List<UserCollect> findCollection(String id);
	
	public List<String> findcollect(String id);

}
