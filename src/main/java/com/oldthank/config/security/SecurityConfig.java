package com.oldthank.config.security;

import com.oldthank.secutity.hanble.MyLogoutSuccessHandler;
import com.oldthank.service.IUserService;
import com.oldthank.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserServiceImpl iUserService;

	@Autowired
	public SecurityConfig(UserServiceImpl iUserService) {
		this.iUserService = iUserService;
	}

	/**
	 * 到目前为止，我们的SecurityConfig只包含有关如何验证用户身份的信息。
	 * Spring Security如何知道我们想要要求所有用户都经过身份验证？
	 * Spring Security如何知道我们想要支持基于表单的身份验证？
	 * 原因是WebSecurity ConfigureAdapter在configure（HttpSecurity http）方法中提供了一个默认配置
	 *
	 * @Author macmini-OldThank
	 * @Description //TODO 
	 * @Date  2022/4/23:4:33 PM
	 * @param http 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//放行路径
				.antMatchers("/user/**").permitAll()
				//非放行的资源，都需要进行认真处理
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//我们必须授予所有用户（即未经验证的用户）访问我们登录页面的权限。表单登录（）。permitAll（）方法允许向所有用户授予与基于表单的登录相关联的所有URL的访问权限。
				.and()
				.logout()
				//退出登陆回调
				.logoutSuccessHandler(new MyLogoutSuccessHandler())
				//指定是否在注销时使HttpSession无效
				.invalidateHttpSession(true)
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(iUserService);
	}
}
