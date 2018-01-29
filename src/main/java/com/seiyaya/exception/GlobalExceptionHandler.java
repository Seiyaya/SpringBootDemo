package com.seiyaya.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author 王佳
 * @created 2018年1月29日 下午6:19:35
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 默认的异常处理方式
	 * @author 王佳
	 * @created 2018年1月29日 下午6:22:18
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e) {
		logger.info("通用的异常处理:-->"+request.getRequestURL().toString());
		ModelAndView model = new ModelAndView();
		model.addObject("" , e);
		model.addObject("url", request.getRequestURL());
		model.setViewName("error");
		return model;
	}
	
//	/**
//	 * 自定义异常处理
//	 * @author 王佳
//	 * @created 2018年1月29日 下午6:24:15
//	 */
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public Object myExceptionHandler(HttpServletRequest request,Exception e){
//		return new Object();
//	}
}
