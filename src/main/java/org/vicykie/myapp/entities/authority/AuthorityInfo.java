package org.vicykie.myapp.entities.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.vicykie.myapp.enums.EntityStatus;

/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role_authority")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityInfo implements GrantedAuthority {

    @Id
    private String id;
    private String authName;
    private String description;
    private String resourcePaths;
    private EntityStatus status = EntityStatus.ENABLE;


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

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return authName;
    }
}
