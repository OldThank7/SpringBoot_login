package com.oldthank.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/role")

public class RoleController {

	@GetMapping("/")
	public String index(){
		return "role";
	}

}
