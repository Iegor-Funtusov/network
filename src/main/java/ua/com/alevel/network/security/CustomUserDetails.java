package ua.com.alevel.network.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ua.com.alevel.network.persistence.type.RoleType;

import java.util.Collection;
import java.util.Date;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 1:57 PM
 */

@Getter
public class CustomUserDetails extends User {

    private Integer id;
    private String role;
    private Date lastLoginTime;
    private RoleType roleType;

    public CustomUserDetails(ua.com.alevel.network.persistence.entity.user.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
        this.id = user.getId();
        this.role = user.getRoleType().name();
        this.lastLoginTime = new Date();
        this.roleType = user.getRoleType();
    }
}
