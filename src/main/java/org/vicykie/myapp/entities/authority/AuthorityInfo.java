package org.vicykie.myapp.entities.authority;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.vicykie.myapp.enums.Status;

import java.io.Serializable;

/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role_authority")
public class AuthorityInfo implements Serializable, GrantedAuthority {

    @Id
    private String id;
    private String authName;
    private String description;
    private String resourcePaths;
    private Status status = Status.ENABLE;
    private RoleInfo roleInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourcePaths() {
        return resourcePaths;
    }

    public void setResourcePaths(String resourcePaths) {
        this.resourcePaths = resourcePaths;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public String getAuthority() {
        return authName;
    }
}
