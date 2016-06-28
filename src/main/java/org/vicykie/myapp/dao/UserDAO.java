package org.vicykie.myapp.dao;


import org.vicykie.myapp.entities.authority.UserInfo;

import java.util.List;

/**
 * Created by vicykie on 2016/5/5.
 */
public interface UserDAO {
    int addUser(UserInfo user);

    int deleteUser(UserInfo user);

    UserInfo getUserById(int id);

    UserInfo getUserByUsername(String username);

    List<UserInfo> getAllUsers();
}
