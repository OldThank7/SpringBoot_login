package com.oldthank.config.security;

import com.oldthank.secutity.UserDetail;
import com.oldthank.secutity.filter.MyUsernamePasswordAuthenticationFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetail UserDetail;

	/**
	 * 到目前为止，我们的SecurityConfig只包含有关如何验证用户身份的信息。
	 * Spring Security如何知道我们想要要求所有用户都经过身份验证？
	 * Spring Security如何知道我们想要支持基于表单的身份验证？
	 * 原因是WebSecurity ConfigureAdapter在configure（HttpSecurity http）方法中提供了一个默认配置
	 * @Author macmini-OldThank
	 * @Description //TODO 
	 * @Date  2022/4/23:4:33 PM
	 * @param http 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//非放行的资源，都需要进行认真处理
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				// 防止iframe 形成跨域
				.and()
				.headers()
				.frameOptions()
				.disable()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf().disable();
		http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.cors().configurationSource(CorsConfigurationSource());
		// 禁用缓存
		http.headers().cacheControl();
	}

	private CorsConfigurationSource CorsConfigurationSource() {
		CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
		corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
		corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
		((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
		return source;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetail);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@SneakyThrows
	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() {

		MyUsernamePasswordAuthenticationFilter upaf =
				new MyUsernamePasswordAuthenticationFilter();

		upaf.setFilterProcessesUrl("/doLogin");

		upaf.setUsernameParameter("username");
		upaf.setPasswordParameter("password");
		upaf.setAuthenticationManager(authenticationManagerBean());

		return upaf;
	}


}
