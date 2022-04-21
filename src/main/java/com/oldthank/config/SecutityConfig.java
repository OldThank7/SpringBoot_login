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
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity // 开启WebSecurity支持
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
				.antMatchers("/doc.html","/webjars/**","/img.icons/**","/swagger-resources/**","/v2/api-docs").permitAll() //允许所有人访问knife4j
				.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.anyRequest().authenticated()// 所有请求必须认证通过
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 认证异常处理
				.and()
				.formLogin()
				.and()
				.cors().configurationSource(CorsConfigurationSource())
				.and()
				.logout()
				.logoutSuccessHandler(new MyLogoutSuccessHandler())
				.and()
				.csrf().disable();
		http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * 配置跨域
     * @return
	 */
	@Bean
	public CorsConfigurationSource CorsConfigurationSource() {
		CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
		corsConfiguration.addAllowedHeader("*");    //header，允许哪些header，本案中使用的是token，此处可将*替换为token；
		corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
		((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
		return source;
	}
}
