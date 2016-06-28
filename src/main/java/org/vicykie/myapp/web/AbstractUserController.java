package org.vicykie.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.service.cache.CacheService;

import java.util.List;

/**
 * Created by vicykie on 2016/6/24.
 */
public class AbstractUserController {
    @Autowired
    private CacheService cacheService;

    @ModelAttribute(value = "roles")
    public List<RoleInfo> bindRoles() {
        List<RoleInfo> roles = cacheService.getRoles();
        return roles;
    }
}
