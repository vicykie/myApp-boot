package org.vicykie.myapp.dao;


import org.springframework.cache.annotation.CacheEvict;
import org.vicykie.myapp.entities.authority.OperationInfo;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.entities.authority.UserInfo;

import java.util.List;

/**
 * Created by vicykie on 2016/5/5.
 */
public interface UserDAO {
    int addUser(UserInfo user);

    int deleteUser(UserInfo user);

    UserInfo getUserById(String id);

    UserInfo getUserByUsername(String username);

    List<UserInfo> getAllUsers();

    @CacheEvict(value = "_myApp_roles", allEntries = true)
    int addRole(RoleInfo role);

    @CacheEvict(value = "_myApp_auths", allEntries = true)
    int addAuth(OperationInfo authorityInfo);

    int updateUser(UserInfo info);

    RoleInfo getRoleById(String roleId);
}
