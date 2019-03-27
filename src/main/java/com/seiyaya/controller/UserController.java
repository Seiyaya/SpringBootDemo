package com.seiyaya.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seiyaya.bean.User;
import com.seiyaya.exception.MyException;
import com.seiyaya.service.UserService;
import com.seiyaya.service.impl.UserServiceImplMybatis;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Resource( name = UserServiceImplMybatis.BEAN_NAME)
	private UserService userService;
	
	
	/**
	 * 获取用户列表
	 * @author 王佳
	 * @created 2018年1月29日 下午2:49:23
	 */
	@RequestMapping(value = "/")
	public String getUserList(ModelMap model) {
		model.addAttribute("userList", userService.queryAllUser());
		return "userList";
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.GET)
	public String showUserForm(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("action", "add");
		return "userForm";
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/users/";
	}
	
	@RequestMapping(value = "/query/{id}" , method = RequestMethod.GET)
	public String queryUser(@PathVariable Long id , ModelMap model) {
		model.addAttribute("user", userService.queryUserById(id));
		model.addAttribute("action", "update");
		return "userForm";
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updateUser(@ModelAttribute User user) {
		userService.update(user);
		return "redirect:/users/";
	}
	
	@RequestMapping(value = "/del/{id}" , method = RequestMethod.GET)
	public String delUser(@PathVariable Long id) {
		userService.delUserById(id);
		return "redirect:/users/";
	}
	
	@GetMapping("/user/exception")
	public String exception() {
		throw new MyException(1,"调用方法失败");
	}
	
	/**
	 *  测试多数据源的情况下添加数据
	 * @author Seiyaya
	 * @date 2019年3月28日 上午2:06:04
	 * @return
	 */
	@GetMapping("/multi/db")
	@ResponseBody
	public Map<String,Object> multiDataSource(){
		Map<String,Object> map = new HashMap<>();
		map.put("result", "111");
		User user = new User("zhangsan", 32, "20190328");
		userService.addUser(user);
		return map;
	}
}
