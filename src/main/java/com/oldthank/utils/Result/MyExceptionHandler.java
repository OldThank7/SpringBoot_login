package com.oldthank.utils.Result;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
	public String exceptionHandler(Exception e){
		System.err.println("未知异常！原因是:"+e);
       	return e.getMessage();
    }

	@ExceptionHandler(value = UsernameNotFoundException.class)
	public String usernamenotfoundexception(Exception e){
		System.err.println("[异常]！原因是:"+e);
		return e.getMessage();
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	public String badcredentialsexception(Exception e){
		System.err.println("[异常]！原因是:"+e);
		return e.getMessage();
	}
}