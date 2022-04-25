package com.oldthank.controller;


import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/getInfo")
	public String getInfo(@PathVariable String token){
		System.out.println("token = " + token);
		return "Hello World!";
	}
}
