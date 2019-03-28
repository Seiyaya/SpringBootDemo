package com.seiyaya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seiyaya.bean.User;
import com.seiyaya.dao.UserMapper;
import com.seiyaya.mapper.UserMapper2;
import com.seiyaya.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "userServiceMybatis")
@Slf4j
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
		userMapper2.addUser(user);
		/**
		  * 此处抛出异常的话，插入的数据是0条
		  * 如果是userMapper2在前面调用，它的事务不会回滚
		 */
		int i = 1/0;
		userMapper.addUser(user);
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

	@Async
	@Override
	public void sendSms() {
		log.info("method start --> 2");
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("",e);
			}
		}
		log.info("method end --> 3");
	}
	
	
}
