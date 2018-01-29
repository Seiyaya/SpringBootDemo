package com.seiyaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seiyaya.bean.User;
import com.seiyaya.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 获取用户列表
	 * @author 王佳
	 * @created 2018年1月29日 下午2:49:23
	 */
	@RequestMapping(method = RequestMethod.GET)
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
		model.addAttribute("action", "query");
		model.addAttribute("user", userService.queryUserById(id));
		return "userForm";
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updateUser(@ModelAttribute User user) {
		userService.update(user);
        return "redirect:/users/";
	}
	
	@RequestMapping(value = "/del/{id}" , method = RequestMethod.POST)
	public String delUser(@PathVariable Long id) {
		userService.delUserById(id);
		return "redirect:/users/";
	}
}
