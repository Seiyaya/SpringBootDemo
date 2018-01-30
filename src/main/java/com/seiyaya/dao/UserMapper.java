package com.seiyaya.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.seiyaya.bean.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user")
	List<User> queryUsers();
	
	@Insert("insert into user(age,birthday,name) values (#{age},#{birthday},#{name})")
	int addUser(User user);
}
