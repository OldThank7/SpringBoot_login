package com.oldthank.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2022-04-23
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    /**
     * 是否激活
     * @Author macmini-OldThank
     * @Description //TODO
     * @Date  2022/4/23:3:17 PM
     * @param null
     * @return null
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 用户是否过期
     * @Author macmini-OldThank
     * @Description //TODO
     * @Date  2022/4/23:3:17 PM
     * @param null
     * @return null
     */
    @TableField("accountNonExpired")
    private Boolean accountNonExpired;

    /**
     * 用户是否锁定
     * @Author macmini-OldThank
     * @Description //TODO
     * @Date  2022/4/23:3:17 PM
     * @param null
     * @return null
     */
    @TableField("accountNonLocked")
    private Boolean accountNonLocked;

    /**
     * 密码是否过期
     * @Author macmini-OldThank
     * @Description //TODO
     * @Date  2022/4/23:3:17 PM
     * @param null
     * @return null
     */
    @TableField("credentialsNonExpired")
    private Boolean credentialsNonExpired;

    /**
     * 关系属性  用来村粗当前用户所有角色信息
     * @Author macmini-OldThank
     * @Description //TODO
     * @Date  2022/4/23:3:19 PM
     * @param null
     * @return null
     */
    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(simpleGrantedAuthority);
        });

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
