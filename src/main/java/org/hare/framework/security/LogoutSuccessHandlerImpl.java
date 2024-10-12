package org.hare.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hare.core.sys.service.SysLoginService;
import org.hare.framework.web.domain.R;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理类
 * @author hare
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final SysLoginService loginService;

    public LogoutSuccessHandlerImpl(SysLoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        loginService.removeLoginUserByUsername(authentication.getName());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), R.success());
    }
}
