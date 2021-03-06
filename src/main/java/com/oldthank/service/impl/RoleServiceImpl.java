package com.oldthank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oldthank.entity.Role;
import com.oldthank.mapper.RoleMapper;
import com.oldthank.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
