package com.oldthank;


import com.oldthank.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLoginApplicationTests {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Test
	void contextLoads() {

	}

}
