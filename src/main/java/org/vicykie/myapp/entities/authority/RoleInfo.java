package org.vicykie.myapp.entities.authority;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vicykie.myapp.enums.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role")
public class RoleInfo implements Serializable {
    @Id
    @Field(value = "id")
    private String id;
    private String roleName;
    private String description;
    private Status status = Status.ENABLE;
    @Field(value = "create_date")
    private Date createDate = new Date();
    @Transient
    private Set<AuthorityInfo> authorities;

    public Set<AuthorityInfo> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityInfo> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
