package com.oldthank.Handler;

import com.oldthank.utils.Result.Result;
import com.oldthank.utils.Result.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		Map<String,Object> respMap = new HashMap<>();

		Result success = Result.fail(ResultCode.REQUEST_LOGOUT);

		respMap.put("successInfo",success);
		respMap.put("exceptionInfo",authException.getMessage());

		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().println(respMap);
	}
}
