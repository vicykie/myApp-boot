package org.vicykie.myapp.config.auth;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.vicykie.myapp.dao.UserDAO;
import org.vicykie.myapp.entities.authority.UserInfo;

/**
 * Created by vicykie on 2016/6/14.
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
    private final UserAuthChecker checker = new UserAuthChecker();
    @Autowired
    UserDAO userDAO;

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("查询用户。。。");
        UserInfo user = userDAO.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("user not found");
        checker.check(user);
        return user;
    }
}
