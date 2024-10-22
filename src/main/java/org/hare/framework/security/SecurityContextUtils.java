package org.hare.framework.security;

import org.hare.core.sys.dto.LoginUserDTO;
import org.hare.framework.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全上下文实用程序
 * @author wang cheng
 */
public class SecurityContextUtils {

    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new BaseException("获取用户ID异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取用户主体ID
     **/
    public static Long getSubjectId() {
        try {
            return getLoginUser().getSubjectId();
        } catch (Exception e) {
            throw new BaseException("获取用户主体ID异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new BaseException("获取用户账户异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUserDTO getLoginUser() {
        try {
            return (LoginUserDTO) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseException("获取用户信息异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
