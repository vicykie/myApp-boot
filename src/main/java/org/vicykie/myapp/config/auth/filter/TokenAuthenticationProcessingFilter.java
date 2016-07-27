package org.vicykie.myapp.config.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vicykie.myapp.config.auth.TokenAuthenticationService;
import org.vicykie.myapp.config.auth.UserAuthentication;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.util.HttpUtils;
import org.vicykie.myapp.vo.ResponseVO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vicykie on 2016/6/15.
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenAuthenticationService authenticationService;

    public TokenAuthenticationProcessingFilter(String defaultFilterProcessesUrl,
                                               AuthenticationManager authenticationManager,
                                               TokenAuthenticationService authenticationService
    ) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
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
        if (!HttpUtils.isAjax(request)) {
            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    request.getParameter("username"), request.getParameter("password"));
            return authenticationManager.authenticate(loginToken);
        } else {
            if (HttpUtils.isFormRequest(contentType)) {
                final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                        request.getParameter("username"), request.getParameter("password"));
                return authenticationManager.authenticate(loginToken);
            } else {
                final UserInfo user = new ObjectMapper().readValue(request.getInputStream(), UserInfo.class);
                final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword());
                return authenticationManager.authenticate(loginToken);

            }

        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String contentType = request.getContentType();
//        // Lookup the complete UserInfo object from the database and create an Authentication for it
//        final UserInfo authenticatedUser = (UserInfo) userDetailsService.loadUserByUsername(authentication.getName());
//
//        // Add the custom token as HTTP header to the response
        final UserInfo user = (UserInfo) authentication.getPrincipal();
        final UserAuthentication userAuthentication = new UserAuthentication(user);
        authenticationService.addAuthentication(response, userAuthentication);
//        response.addHeader(AUTH_HEADER_NAME, tokenHandler.generateToken(user));
        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        if (!HttpUtils.isAjax(request)) {
            response.sendRedirect(request.getContextPath() + "/user/index");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(ResponseVO.loginSuccess("/user/index"));
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        }
    }

}
