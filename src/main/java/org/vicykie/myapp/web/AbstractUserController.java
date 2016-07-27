package org.vicykie.myapp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.vicykie.myapp.entities.authority.AuthorityInfo;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.service.cache.CacheService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by vicykie on 2016/6/24.
 */
public class AbstractUserController {
    @Autowired
    CacheService redisCacheService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @ModelAttribute(value = "authList")
    public List<AuthorityInfo> bindAuthList() {
        List<AuthorityInfo> authList = redisCacheService.getAuthList();
        return authList;
    }

    @ModelAttribute(value = "roleList")
    public List<RoleInfo> bindRoleList() {
        List<RoleInfo> roleInfoList = redisCacheService.getRoles();
        return roleInfoList;
    }

}
