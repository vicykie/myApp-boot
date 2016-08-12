package org.vicykie.myapp.config.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.vicykie.myapp.config.auth.TokenAuthenticationService;
import org.vicykie.myapp.config.auth.UserAuthentication;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.util.HttpUtils;
import org.vicykie.myapp.util.RequestUtil;
import org.vicykie.myapp.vo.ResponseVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vicykie on 2016/6/7.
 */
@Component("loginSuccessHandler")
@ConfigurationProperties(prefix = "app")
public class StatelessLoginSuccessHandler implements AuthenticationSuccessHandler {
    private String loginSuccessUrl;

    public String getLoginSuccessUrl() {
        return loginSuccessUrl;
    }

    public void setLoginSuccessUrl(String loginSuccessUrl) {
        this.loginSuccessUrl = loginSuccessUrl;
    }

    public TokenAuthenticationService getTokenService() {
        return tokenService;
    }

    public void setTokenService(TokenAuthenticationService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    private TokenAuthenticationService tokenService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //登录成功处理
        final UserInfo user = (UserInfo) authentication.getPrincipal();
        final UserAuthentication userAuthentication = new UserAuthentication(user);
        tokenService.addAuthentication(response, userAuthentication);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        if (!HttpUtils.isAjax(request)) {
            response.sendRedirect(request.getContextPath() + loginSuccessUrl);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            response.setHeader("Content-Type", "html/json;charset=UTF-8");
            String str = mapper.writeValueAsString(ResponseVO.loginSuccess(loginSuccessUrl));
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        }
    }


}
