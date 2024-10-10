package org.hare.core.sys.service;

import org.hare.core.sys.dto.LoginRequest;
import org.hare.core.sys.dto.LoginUserDTO;

/**
 * 登录接口
 * @author wang cheng
 */
public interface SysLoginService {

    /**
     * 令牌
     * @param request
     * @return token
     */
    String token(LoginRequest request);

    /**
     * 缓存登用户
     * @param dto
     * @return LoginUserDTO
     */
    LoginUserDTO cacheLoginUser(LoginUserDTO dto);
}
