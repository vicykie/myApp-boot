package org.vicykie.myapp.config.auth.filter;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.vicykie.myapp.config.auth.TokenAuthenticationService;
import org.vicykie.myapp.config.auth.UserAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by vicykie on 2016/6/14.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
    private String[] excludUri = {"/", "/auth/login"};
    private final TokenAuthenticationService tokenAuthenticationService;

    public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
        this.tokenAuthenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserAuthentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response); // always continue

//        SecurityContextHolder.getContext().setAuthentication(null);
    }


}
