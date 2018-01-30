package com.seiyaya.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.seiyaya.bean.User;
import com.seiyaya.service.UserService;

/**
 * 使用jdbcTemplate访问数据库
 * @author 王佳
 * @created 2018年1月30日 上午9:39:38
 */
@Service(value  = "userServiceByJdbcTemplate")
public class UserServiceImplJDBCTemplate implements UserService{

	public static final String BEAN_NAME = "userServiceByJdbcTemplate";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> queryAllUser() {
		List<User> userList = jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<User>(User.class));
		return userList;
	}

	@Override
	public void addUser(User user) {
		jdbcTemplate.update("insert into user(age,birthday,name) values (?,?,?)",new Object[] {user.getAge(),
				user.getBirthday(),user.getName()});
	}

	@Override
	public User queryUserById(Long id) {
		User user = jdbcTemplate.queryForObject("select * from user where id = ?", new BeanPropertyRowMapper<>(User.class) , id);
		return user;
	}

	@Override
	public void update(User user) {
		List<Object> param  = new ArrayList<>();
		StringBuilder paramSql = new StringBuilder();
		if(StringUtils.isNotBlank(user.getName())) {
			param.add(user.getName());
			paramSql.append(" name = ? ,");
		}
		if(StringUtils.isNotBlank(user.getBirthday())) {
			param.add(user.getBirthday());
			paramSql.append(" birthday = ? ,");
		}
		if(StringUtils.isNotBlank(user.getAge()+"")) {
			param.add(user.getAge());
			paramSql.append(" age = ? ,");
		}
		String sql = paramSql.deleteCharAt(paramSql.length()-1).toString();
		if(paramSql.toString().length()!=0 && user.getId()!=null) {
			param.add(user.getId());
			jdbcTemplate.update("update user set " + sql + " where id = ?", param.toArray());
		}
	}

	@Override
	public void delUserById(Long id) {
		jdbcTemplate.update("delete from user where id = ?" , id);
	}

}
