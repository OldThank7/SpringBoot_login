package com.oldthank.Handler;

import com.oldthank.utils.Result.Result;
import com.oldthank.utils.Result.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		Map<String,Object> respMap = new HashMap<>();

		Result success = Result.fail(ResultCode.USER_LOGIN_ERROR);

		respMap.put("successInfo",success);
		respMap.put("exceptionInfo",exception.getMessage());

		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().println(respMap);
	}
}
