package org.vicykie.myapp.config.auth;

import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Component;
import org.vicykie.myapp.entities.authority.UserInfo;

/**
 * Created by vicykie on 2016/6/15.
 */
@Component
@Log4j
public class UserAuthChecker extends AccountStatusUserDetailsChecker {

    public void check(UserInfo user) {
        log.info("检查用户合法性");
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
                            "UserInfo account have expired"));
        }

//        if (user.isCredentialsNonExpired()) {
//            throw new CredentialsExpiredException(messages.getMessage(
//                    "AccountStatusUserDetailsChecker.credentialsExpired",
//                    "UserInfo credentials have expired"));
//        }

    }
}
