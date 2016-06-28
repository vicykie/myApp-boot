package org.vicykie.myapp.service.cache;

import org.springframework.cache.annotation.Cacheable;
import org.vicykie.myapp.entities.authority.RoleInfo;

import java.util.List;

/**
 * Created by vicykie on 2016/6/24.
 */
public interface CacheService {

    @Cacheable(key = "'_myApp_roles'")
    List<RoleInfo> getRoles();
}
