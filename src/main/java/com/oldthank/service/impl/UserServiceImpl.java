package com.oldthank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oldthank.entity.User;
import com.oldthank.mapper.UserMapper;
import com.oldthank.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{


}
