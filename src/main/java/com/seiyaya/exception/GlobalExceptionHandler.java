package com.seiyaya.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 * @author 王佳
 * @created 2018年1月29日 下午6:19:35
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
	  * 自定义异常处理
	 * @author 王佳
	 * @created 2018年1月29日 下午6:24:15
	 */
	@ExceptionHandler(MyException.class)
	@ResponseBody
	public Map<String,Object> myExceptionHandler(HttpServletRequest request,MyException e){
		Map<String, Object> result = new HashMap<>();
		result.put("error_code", e.getErrorType());
		result.put("error_msg", e.getMessage());
		log.error("自定义异常信息",e);
		return result;
	}
	
	/**
	 * 默认的异常处理方式
	 * @author 王佳
	 * @created 2018年1月29日 下午6:22:18
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e) {
		log.info("通用的异常处理:-->{}",request.getRequestURL().toString());
		ModelAndView model = new ModelAndView();
		model.addObject("" , e);
		model.addObject("url", request.getRequestURL());
		model.setViewName("error");
		return model;
	}
}
