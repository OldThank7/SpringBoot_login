package com.oldthank.config;


import com.oldthank.Handler.MyAuthenticationEntryPoint;
import com.oldthank.Handler.MyFailureHandler;
import com.oldthank.Handler.MyLogoutSuccessHandler;
import com.oldthank.Handler.MySuccessHandler;
import com.oldthank.secutity.filter.MyUsernamePasswordAuthenticationFilter;
import com.oldthank.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecutityConfig extends WebSecurityConfigurerAdapter {


	private UserServiceImpl userserviceimpl;

	@Autowired
	public SecutityConfig(UserServiceImpl userserviceimpl) {
		this.userserviceimpl = userserviceimpl;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userserviceimpl);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {

		MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();

		myUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/doLogin");

		myUsernamePasswordAuthenticationFilter.setUsernameParameter("username");   //指定接受用户名的 key
		myUsernamePasswordAuthenticationFilter.setPasswordParameter("password");   //指定接受用户密码的 kay

		myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

		myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new MySuccessHandler());
		myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new MyFailureHandler());

		return myUsernamePasswordAuthenticationFilter;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//公共资源
				.mvcMatchers("/index/**").permitAll()
				.mvcMatchers("/TestController/**").permitAll()
				.antMatchers("/swagger/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				.antMatchers("/v2/api-docs-ext/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/doc.html").permitAll()
				.mvcMatchers("/kaptcha").permitAll()
				// 所有请求必须认证通过
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 认证异常处理
				.and()
				.formLogin()
				.and()
				.logout()
				.logoutSuccessHandler(new MyLogoutSuccessHandler())
				.and()
				.csrf().disable()
		;
		http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
