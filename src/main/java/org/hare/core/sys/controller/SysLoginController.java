package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.LoginRequest;
import org.hare.core.sys.service.SysLoginService;
import org.hare.framework.web.domain.R;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录/登出
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
public class SysLoginController {

    private final SysLoginService loginService;

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @PostMapping("/token")
    public R token(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return R.success(loginService.token(loginRequest));
    }

}