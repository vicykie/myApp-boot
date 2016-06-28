package org.vicykie.myapp.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.util.UserAuthTokenHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vicykie on 2016/6/14.
 */

@Service("tokenService")
public class TokenAuthenticationService {
    private final static String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    @Autowired
    private UserAuthTokenHandler tokenHandler;


    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final UserInfo user = (UserInfo) authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.generateToken(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final UserInfo user = tokenHandler.parseToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
