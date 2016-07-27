package org.vicykie.myapp.config.auth.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.vicykie.myapp.entities.authority.RoleInfo;
import org.vicykie.myapp.entities.authority.UserInfo;
import org.vicykie.myapp.enums.EntityStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicykie on 2016/6/15.
 */
@Component
@ConfigurationProperties(prefix = "auth.token")
public class UserAuthTokenHandler {
    private static Logger logger = LogManager.getLogger(UserAuthTokenHandler.class);
    private final String AUDIENCE_UNKNOWN = "unknown";
    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";
    //claims key
    private final String CLAIMS_USER_NAME = "name";
    private final String CLAIMS_ADDRESS = "address";
    private final String CLAIMS_CREATED = "created";
    private final String CLAIMS_ROLES = "roles";
    private final String CLAIMS_USER_EXPIRE_DATE = "expire_date";
    private final String CLAIMS_USER_CREATE_DATE = "create_date";

    private String securityKey;

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    private long expiration = 604800L;//7days

    public String generateToken(UserInfo user) {
        String username = user.getUsername();
        String name = user.getName();
        String address = user.getAddress();
        RoleInfo role = user.getRoleInfo();
        Date createDate = user.getCreateDate();
        Date expireDate = user.getExpireDate();
        /**
         * 放入相关信息
         */
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.AUDIENCE, AUDIENCE_WEB);
        claims.put(CLAIMS_CREATED, this.generateCurrentDate());
        claims.put(CLAIMS_USER_NAME, name);
        claims.put(CLAIMS_ADDRESS, address);
        claims.put(CLAIMS_ROLES, role);
        claims.put(CLAIMS_USER_CREATE_DATE, createDate);
        claims.put(CLAIMS_USER_EXPIRE_DATE, expireDate);
        claims.put(Claims.EXPIRATION, this.generateExpirationDate());
        logger.info("token: " + this.generateToken(claims));
        return this.generateToken(claims);

    }


    public UserInfo parseToken(String token) {
        UserInfo user = new UserInfo();
        final Claims claims = this.getClaimsFromToken(token);
        user.setUsername(claims.getSubject());
        user.setName((String) claims.get(CLAIMS_USER_NAME));
        user.setCreateDate(new Date((Long) claims.get(CLAIMS_USER_CREATE_DATE)));
        user.setExpireDate(new Date((Long) claims.get(CLAIMS_USER_EXPIRE_DATE)));
        RoleInfo roleInfo = getRoleInfo(claims);
        user.setRoleInfo(roleInfo);
        return user;
    }

    private RoleInfo getRoleInfo(Claims claims) {
        RoleInfo roleInfo = new RoleInfo();
        Map role = (Map) claims.get(CLAIMS_ROLES);
        roleInfo.setCreateDate(new Date((Long) role.get("createDate")));
        roleInfo.setDescription((String) role.get("description"));
        roleInfo.setEntityStatus(EntityStatus.values()[(int) role.get("entityStatus")]);
        roleInfo.setRoleName((String) role.get("roleName"));
        roleInfo.setId((String) role.get("id"));
        return roleInfo;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIMS_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }


    /**
     * 是否可以更新token
     *
     * @param token
     * @param lastPasswordReset
     * @return
     */
    public Boolean canTokenBeRefresh(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());//更新时间
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserInfo user) {

        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
//        final Date expiration = this.getExpirationDateFromToken(token);
        return (username.equals(user.getUsername())
                && !(this.isTokenExpired(token))
                && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
    }


    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
    }


    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, securityKey)
                .compact();
    }


    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


}
