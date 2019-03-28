package com.seiyaya.controller;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seiyaya.service.UserService;
import com.seiyaya.service.impl.UserServiceImplMybatis;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IndexController {
	
	@Resource( name = UserServiceImplMybatis.BEAN_NAME)
	private UserService userService;
	
	@RequestMapping("/say")
	public String say() {
		return "say";
	}
	
	
	@Test
	public void testSay() {
		assertEquals("say", new IndexController().say());
	}
	
	@GetMapping("/sms")
	public String sendSMS() {
		log.info("method --> start  1");
		userService.sendSms();
		log.info("method --> end  4");
		return "ok";
	}
}
