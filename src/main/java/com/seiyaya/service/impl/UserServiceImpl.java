package com.seiyaya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seiyaya.bean.User;
import com.seiyaya.dao.UserRepository;
import com.seiyaya.service.UserService;

@Service(value = "userServiceByJPA")
public class UserServiceImpl implements UserService {
	
	public static final String BEAN_NAME = "userServiceByJPA";

	@Autowired
	private UserRepository userDao;
	
	@Override
	public List<User> queryAllUser() {
		List<User> userList = userDao.findAll();
		return userList;
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public User queryUserById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public void update(User user) {
		userDao.save(user);
	}

	@Override
	public void delUserById(Long id) {
		userDao.delete(id);
	}

	@Override
	public void sendSms() {
		// TODO Auto-generated method stub
		
	}

}
