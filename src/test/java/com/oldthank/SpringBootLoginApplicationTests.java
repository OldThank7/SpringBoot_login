package com.oldthank;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oldthank.entity.Role;
import com.oldthank.entity.User;
import com.oldthank.service.impl.UserServiceImpl;
import com.oldthank.utils.Result.ResultBody;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootLoginApplicationTests {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Test
	void contextLoads() {

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username","admin");
		User userById = userServiceImpl.getOne(queryWrapper);

		Integer id = userById.getId();

		List<Role> byUid = userServiceImpl.getByUid(id);
		userById.setRoles(byUid);

		ResultBody success = ResultBody.success(userById);
		System.out.println(success);

	}

}
