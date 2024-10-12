package org.hare.core.sys.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.LoginRequest;
import org.hare.core.sys.dto.LoginUserDTO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.core.sys.service.SysLoginService;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.exception.BaseException;
import org.hare.framework.security.JwtBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     令牌服务实现
 * </p>
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysLoginServiceImpl implements SysLoginService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SysUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtBuilder jwtBuilder;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String token(LoginRequest request) {
        final String username = request.getUsername();
        final String password = request.getPassword();

        // 根据账号获取用户信息
        final SysUserDO sysUser = userService.findByUsername(username);

        try {
            Assert.notNull(sysUser, username + " 用户不存在");

            Assert.isTrue(Constants.NORMAL.equals(sysUser.getStatus()), username + " 用户被限制");
            // 验证密码
            Assert.isTrue(passwordEncoder.matches(password, sysUser.getPassword()), "密码错误");
        } catch (Exception e) {
            logger.error("登录异常：{}", e.toString());
            throw new BaseException(SysConstants.LOGIN_ERROR_MSG);
        }
        cacheLoginUser(LoginUserDTO.withSysUser(sysUser).build());
        return jwtBuilder.build(sysUser.getUsername());
    }

    @Override
    public LoginUserDTO cacheLoginUser(LoginUserDTO dto) {
        try {
            final String value = new ObjectMapper().writeValueAsString(dto);
            redisTemplate.opsForValue().set(SysConstants.LOGIN_USER_PREFIX + dto.getUsername(), value,
                    jwtBuilder.getExpireTime(), TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("缓存登录用户异常：{}", e.toString());
            throw new BaseException("缓存登录用户异常：" + e);
        }

        return dto;
    }

    @Override
    public void removeLoginUserByUsername(String username) {
        redisTemplate.delete(SysConstants.LOGIN_USER_PREFIX + username);
    }

    @Override
    public LoginUserDTO getLoginUserByUsername(String username) {
        final String data = redisTemplate.opsForValue().get(SysConstants.LOGIN_USER_PREFIX + username);
        LoginUserDTO dto = null;

        try {
            if (StringUtils.hasText(data)) {
                dto = new ObjectMapper().readValue(data, LoginUserDTO.class);
            }
        } catch (JsonProcessingException e) {
            logger.error("登录用户Json转换异常：{}", e.toString());
            throw new BaseException("登录用户Json转换异常：" + e);
        }
        return dto;
    }

}
