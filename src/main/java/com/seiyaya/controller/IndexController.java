package com.seiyaya.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@RequestMapping("/say")
	public String say() {
		return "say";
	}
	
	
	@Test
	public void testSay() {
		assertEquals("say", new IndexController().say());
	}
}
