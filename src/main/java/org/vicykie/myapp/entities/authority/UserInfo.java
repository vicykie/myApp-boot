package org.vicykie.myapp.entities.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vicykie.myapp.enums.EntityStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by vicykie on 2016/5/5.
 */
@Document(collection = "user")//指定collection的名字
public class UserInfo implements UserDetails {
    @Id
    private String id;
    private String username;
    //    @JsonIgnore
    private String password;
    private String name;
    private int age;
    private int score;
    private RoleInfo roleInfo;
    @Field(value = "create_date")
    private Date createDate = new Date();
    @Field("expire_date")
    private Date expireDate;
    @Field("last_password_reset")
    @JsonIgnore
    private Date lastPasswordReset;
    private EntityStatus entityStatus = EntityStatus.ENABLE;
    private String salt;
    private String address;
    private boolean isLocked;
//    @Transient
//    private final Collection<? extends GrantedAuthority> authorities;

    public UserInfo(String username, String name, EntityStatus entityStatus, int score, int age) {
        this.username = username;
        this.name = name;
        this.entityStatus = entityStatus;
        this.score = score;
        this.age = age;
//        this.authorities = this.getAuthorities();
    }

    public UserInfo() {
//        this.authorities = this.getAuthorities();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = null;
        if (this.roleInfo != null) {
            authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(this.roleInfo.getRoleName());
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
