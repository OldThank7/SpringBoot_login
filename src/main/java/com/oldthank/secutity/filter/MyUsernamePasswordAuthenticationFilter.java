package com.oldthank.secutity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.out.println(" ======================================================= ");
		//1、判断是否为POST请求
		System.out.println("request.getMethod() = " + request.getMethod());
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		//2、判断是否为 JSON 格式数据
		String contentType = request.getContentType();
		System.out.println("contentType = " + contentType);
		if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			try {
				//3、从JSON数据中获取用户名和密码和验证码
				ServletInputStream inputStream = request.getInputStream();

				Map<String, String> map = new ObjectMapper().readValue(inputStream, Map.class);

				String username = map.get("username");
				String password = map.get("password");

				System.out.println("username = " + username);
				System.out.println("password = " + password);

				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
				// Allow subclasses to set the "details" property
				setDetails(request, authRequest);
				return this.getAuthenticationManager().authenticate(authRequest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}
