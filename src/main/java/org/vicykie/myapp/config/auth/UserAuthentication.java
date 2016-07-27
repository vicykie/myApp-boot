package org.vicykie.myapp.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.vicykie.myapp.entities.authority.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by vicykie on 2016/6/14.
 */
public class UserAuthentication implements Authentication {
    private final UserInfo user;
    private boolean authenticated = true;

    public UserAuthentication(UserInfo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = null;
        if (user.getRoleInfo() != null) {
            authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleInfo().getRoleName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
