package org.vicykie.myapp.config.auth.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vicykie.myapp.util.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by vicykie on 2016/6/15.
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;


    public TokenAuthenticationProcessingFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.authenticationManager = authenticationManager;

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String contentType = request.getContentType();
//        String method = request.getMethod();
//        if (method.equalsIgnoreCase("GET")) {
//            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "不支持的请求方式，请用POST方式提交登录请求");
//            return null;
//        }
        if (!HttpUtils.isFormRequest(contentType)) {
            ServletInputStream inp = request.getInputStream();
            JSONObject userJson = JSONObject.parseObject(inp, Charset.forName("utf-8"), null);
            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    userJson.get("username"), userJson.get("password"));
            return authenticationManager.authenticate(loginToken);
        } else {
            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    request.getParameter("username"), request.getParameter("password"));
            return authenticationManager.authenticate(loginToken);
        }

    }

}
