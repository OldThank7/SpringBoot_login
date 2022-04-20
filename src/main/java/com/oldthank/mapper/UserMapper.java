package com.oldthank.mapper;

import com.oldthank.entity.Role;
import com.oldthank.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	List<Role> getByUid(@Param("uid") Integer id);

}
