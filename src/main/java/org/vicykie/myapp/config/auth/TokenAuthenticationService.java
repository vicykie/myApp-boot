package org.vicykie.myapp.config.auth;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.vicykie.myapp.config.auth.handler.UserAuthTokenHandler;
import org.vicykie.myapp.entities.authority.UserInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * Created by vicykie on 2016/6/14.
 */

@Service("tokenService")
@Log4j
public class TokenAuthenticationService {
    private final static String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    @Autowired
    private UserAuthTokenHandler tokenHandler;


    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        SecurityContextHolder.clearContext();
        final UserInfo user = authentication.getDetails();
        String token = tokenHandler.generateToken(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        Cookie cookie = new Cookie(AUTH_HEADER_NAME, token);
//        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    public UserAuthentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            log.info(MessageFormat.format("get token from request header 【{0}】 ", token));
            final UserInfo user = tokenHandler.parseToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        } else {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase(AUTH_HEADER_NAME)) {
                        token = cookie.getValue();
                        log.info(MessageFormat.format("get token from cookie【{0}】 ", token));
                        UserInfo user = tokenHandler.parseToken(token);
                        if (user != null) {
                            return new UserAuthentication(user);
                        }
                    }
                }
            }
        }
        return null;
    }
}
