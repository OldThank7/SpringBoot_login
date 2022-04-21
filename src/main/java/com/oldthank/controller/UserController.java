package com.oldthank.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/doLoginUser")
	public String doLoginUser(String username,String password){
		return username + ":" + password;
	}
	@GetMapping("/getUserName")
	public String getUserName() {
		System.out.println("this = " + this);
		return "UserName";
	}

	@PostMapping("/getUserByEmail")
	public String getUserByEmail() {
		return "404290996@qq.com";
	}

}
