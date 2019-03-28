package com.seiyaya.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.seiyaya.bean.User;

@Mapper
public interface UserMapper2 {
	
	@Insert("insert into user(age,birthday,name) values (#{age,jdbcType=INTEGER},#{birthday,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR})")
	int addUser(User user);
}
