package org.vicykie.myapp.entities.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.vicykie.myapp.enums.EntityStatus;

/**
 * Created by vicykie on 2016/5/10.
 */
@Document(collection = "role_authority")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//http://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security
public class OperationInfo implements GrantedAuthority {

    @Id
    private String id;
    private String oprName;
    private String remark;
    private String resourcePaths;
    private EntityStatus status = EntityStatus.ENABLE;
    @JsonIgnore
    @Override
    public String getAuthority() {
        return oprName;
    }
}
