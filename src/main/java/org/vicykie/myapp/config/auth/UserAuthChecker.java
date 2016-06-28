package org.vicykie.myapp.config.auth;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by vicykie on 2016/6/15.
 */
public class UserAuthChecker extends AccountStatusUserDetailsChecker {
    @Override
    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.locked", "UserInfo account is locked"));
        }

        if (!user.isEnabled()) {
            throw new DisabledException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.disabled", "UserInfo is disabled"));
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(
                    messages.getMessage("AccountStatusUserDetailsChecker.expired",
                            "UserInfo account has expired"));
        }

        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.credentialsExpired",
                    "UserInfo credentials have expired"));
        }

    }
}
