package com.oldthank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oldthank.entity.Role;
import com.oldthank.entity.User;
import com.oldthank.mapper.UserMapper;
import com.oldthank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();

		lambdaQueryWrapper.eq(User::getUsername,username);

		User user = userMapper.selectOne(lambdaQueryWrapper);

		if (ObjectUtils.isEmpty(user))throw new UsernameNotFoundException("用户名不存在");

		List<Role> byuid =  userMapper.getByUid(user.getId());

		user.setRoles(byuid);

		return user;
	}
}
