package com.seiyaya.exception;

/**
 * 不同的异常会被不同的全局异常处理器捕捉
 * @author Seiyaya
 *
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int errorType;//
	
	public int getErrorType() {
		return errorType;
	}

	public MyException(int errorType) {
		super();
		this.errorType = errorType;
	}

	public MyException(int errorType, String errMsg) {
		super(errMsg);
		this.errorType = errorType;
	}

	public MyException(int errorType, Throwable t) {
		super(t);
		this.errorType = errorType;
	}

	public MyException(int errorType, String errMsg, Throwable t) {
		super(errMsg, t);
		this.errorType = errorType;
	}
}
