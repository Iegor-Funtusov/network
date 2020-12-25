package ua.com.alevel.network.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.network.persistence.listener.AgeByBirthDayGenerationListener;
import ua.com.alevel.network.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.network.persistence.type.GenderType;
import ua.com.alevel.network.persistence.type.RoleType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:36 AM
 */

@Getter
@Setter
@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
        AgeByBirthDayGenerationListener.class
})
public class Personal extends User {

    @Column(name = "GENDER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private GenderType genderType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}
