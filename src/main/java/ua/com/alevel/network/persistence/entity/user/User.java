package ua.com.alevel.network.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.network.persistence.entity.AbstractEntity;
import ua.com.alevel.network.persistence.type.RoleType;

import javax.persistence.*;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:35 AM
 */

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE", nullable = false)
    private RoleType roleType;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    public User() {
        super();
        this.enabled = true;
    }
}
