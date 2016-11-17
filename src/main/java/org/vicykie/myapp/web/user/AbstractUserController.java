package org.vicykie.myapp.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.vicykie.myapp.entities.authority.OperationInfo;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.service.cache.CacheService;

import java.util.List;

/**
 * Created by vicykie on 2016/6/24.
 */
public class AbstractUserController {
    @Autowired
    private CacheService redisCacheService;
    @ModelAttribute(value = "authList")
    public List<OperationInfo> bindAuthList() {
        List<OperationInfo> authList = redisCacheService.getAuthList();
        return authList;
    }

    @ModelAttribute(value = "roleList")
    public List<RoleInfo> bindRoleList() {
        List<RoleInfo> roleInfoList = redisCacheService.getRoles();
        return roleInfoList;
    }

}
