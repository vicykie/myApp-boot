package org.vicykie.myapp.config.auth.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private Environment environment;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String successUrl = environment.getProperty("login.success.url");
        //登录成功处理
        if (RequestUtil.isAjax(request)) {
            //authentication.getDetails();
            //ajax返回json数据
            PrintWriter writer = response.getWriter();
            response.setHeader("Content-Type", "html/json;charset=UTF-8");
//            response.setContentType("application/json;charset=UTF-8");
            writer.print(JSONObject.toJSONString(ResponseVO.loginSuccess(successUrl)));
            writer.flush();
            writer.close();
        } else {
            //跳转页面
            response.sendRedirect(successUrl);

        }
    }


}
