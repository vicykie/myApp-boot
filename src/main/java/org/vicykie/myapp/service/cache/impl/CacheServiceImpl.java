package org.vicykie.myapp.service.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.vicykie.myapp.entities.authority.AuthorityInfo;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.service.cache.CacheService;

import java.util.List;

/**
 * Created by vicykie on 2016/6/24.
 */
@Service(value = "redisCacheService")
public class CacheServiceImpl implements CacheService {
    @Autowired
    private MongoTemplate template;

    @Override
    public List<RoleInfo> getRoles() {
        return template.findAll(RoleInfo.class);
    }

    @Override
    public List<AuthorityInfo> getAuthList() {
        return template.findAll(AuthorityInfo.class);
    }
}
