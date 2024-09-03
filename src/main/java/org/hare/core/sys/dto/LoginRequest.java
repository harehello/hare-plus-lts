package org.hare.core.sys.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录对象
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class LoginRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
