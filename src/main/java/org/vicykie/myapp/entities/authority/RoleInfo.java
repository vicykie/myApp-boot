package org.vicykie.myapp.entities.authority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.vicykie.myapp.enums.EntityStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RoleInfo implements GrantedAuthority {
    @Id
    @Field(value = "id")
    private String id;
    private String roleName;
    private String description;
    private EntityStatus entityStatus = EntityStatus.ENABLE;
    @Field(value = "create_date")
    private Date createDate = new Date();
    private Set<OperationInfo> operations = new HashSet<>();

    @Override
    public String getAuthority() {
        return roleName;
    }
}
