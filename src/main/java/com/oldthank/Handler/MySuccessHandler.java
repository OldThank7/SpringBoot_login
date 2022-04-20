package com.oldthank.Handler;

import com.oldthank.utils.Result.Result;
import com.oldthank.utils.Result.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MySuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Map<String,Object> respMap = new HashMap<>();

		Result success = Result.success(ResultCode.SUCCESS);

		respMap.put("successInfo",success);
		respMap.put("authenticationInfo",authentication);

		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().println(respMap);
	}
}
