package ua.com.alevel.network.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.network.persistence.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:43 AM
 */

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {
        super();
        setRoleType(RoleType.ROLE_ADMIN);
    }
}
