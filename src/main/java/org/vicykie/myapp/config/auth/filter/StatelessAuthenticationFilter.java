package org.vicykie.myapp.config.auth.filter;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.vicykie.myapp.config.auth.TokenAuthenticationService;
import org.vicykie.myapp.config.auth.UserAuthChecker;
import org.vicykie.myapp.config.auth.UserAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by vicykie on 2016/6/14.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
    private String[] excludeUriList = {"/", "/auth/login", "/401.html", "/404.html",
            "/500.html"};
    private final TokenAuthenticationService tokenAuthenticationService;
    private final UserAuthChecker userAuthChecker;

    public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService, UserAuthChecker userAuthChecker) {
        this.tokenAuthenticationService = authenticationService;
        this.userAuthChecker = userAuthChecker;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        List<String> excludes = CollectionUtils.arrayToList(excludeUriList);
        HttpServletRequest re = (HttpServletRequest) request;
        String uri = re.getRequestURI();
        String staticPath = "/static";
        System.out.println(uri);
        if (!excludes.contains(uri) && !uri.startsWith(staticPath) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserAuthentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request);
            if (authentication!=null)
            userAuthChecker.check(authentication.getDetails());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response); // always continue
    }


}
