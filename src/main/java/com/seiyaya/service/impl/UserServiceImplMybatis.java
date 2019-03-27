package com.seiyaya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seiyaya.bean.User;
import com.seiyaya.dao.UserMapper;
import com.seiyaya.mapper.UserMapper2;
import com.seiyaya.service.UserService;

@Service(value = "userServiceMybatis")
public class UserServiceImplMybatis implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMapper2 userMapper2;
	
	public static final String BEAN_NAME = "userServiceMybatis";

	@Override
	public List<User> queryAllUser() {
		return userMapper.queryUsers();
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
		userMapper2.addUser(user);
	}

	@Override
	public User queryUserById(Long id) {
		return null;
	}

	@Override
	public void update(User user) {
		
	}

	@Override
	public void delUserById(Long id) {
		
	}
	
	
}
