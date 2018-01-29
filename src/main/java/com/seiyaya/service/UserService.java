package com.seiyaya.service;

import java.util.List;

import com.seiyaya.bean.User;

public interface UserService {

	/**
	 * 查询所有的用户
	 * @author 王佳
	 * @created 2018年1月29日 下午2:59:03
	 */
	List<User> queryAllUser();

	/**
	 * 添加用户
	 * @author 王佳
	 * @created 2018年1月29日 下午2:58:59
	 */
	void addUser(User user);

	/**
	 * 通过id查询用户
	 * @author 王佳
	 * @created 2018年1月29日 下午3:01:02
	 */
	User queryUserById(Long id);

	/**
	 * 更新用户
	 * @author 王佳
	 * @created 2018年1月29日 下午3:03:01
	 */
	void update(User user);

	/**
	 * 根据用户id删除用户
	 * @author 王佳
	 * @created 2018年1月29日 下午3:04:50
	 */
	void delUserById(Long id);
	
}
