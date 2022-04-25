package com.oldthank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oldthank.entity.Role;
import com.oldthank.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	public User getUserById(@Param("username")String username);

	List<Role> getByUid(@Param("uid") Integer uid);

}
