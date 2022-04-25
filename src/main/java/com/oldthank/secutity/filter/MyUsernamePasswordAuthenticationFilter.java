package com.oldthank.secutity.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oldthank.entity.User;
import com.oldthank.utils.Result.ResultBody;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


	/**
	 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
	 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
	 * attemptAuthentication：接收并解析用户凭证。
	 * successfulAuthentication：用户成功登陆后，这个方法会被调用，咱们在这个方法里生成token并返回。
	 */
	/**
	 *
	 * 需求：
	 * 1、必须Post请求
	 * 2、JSON格式
	 * 3、拿到用户名、密码，进行比对
	 *
	 * @Author macmini-OldThank
	 * @Description //TODO
	 * @Date  2022/4/23:8:01 PM
	 * @param request
	 * @param response
	 * @return org.springframework.security.core.Authentication
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			try{
				Map readValue = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				String username = (String) readValue.get(this.SPRING_SECURITY_FORM_USERNAME_KEY);
				String password = (String) readValue.get(this.SPRING_SECURITY_FORM_PASSWORD_KEY);

				System.out.println("password = " + password);

				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
				setDetails(request, authRequest);
				return this.getAuthenticationManager().authenticate(authRequest);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		throw new BadCredentialsException("用户名不存在");
	}

	/**
	 * 认证成功
	 * @Author macmini-OldThank
	 * @Description //TODO
	 * @Date  2022/4/24:5:16 PM
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

		User jwtUser = (User) authResult.getPrincipal();

		 ResultBody success = ResultBody.success(jwtUser);

		String username = jwtUser.getUsername();
		Map<String,Object> map = new HashMap<>();
		map.put("username",username);

		response.setStatus(200);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		Map<String,Object> respMap = new HashMap<>();
		respMap.put("success",success);

		response.setStatus(200);
		response.getWriter().println(JSONObject.toJSONString(respMap));

	}

	/**
	 * 认证失败
	 * @Author macmini-OldThank
	 * @Description //TODO
	 * @Date  2022/4/24:5:16 PM
	 * @param request : 请求
	 * @param response ：相应
	 * @param failed ： 失败的错误信息
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.getWriter().write("authentication failed, reason: " + failed.getMessage());
	}
}
