package ua.com.alevel.network.web.data.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.network.persistence.type.GenderType;

import java.util.Date;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 10:25 AM
 */

@Getter
@Setter
public class PersonalData extends AbstractEntityData {

    private String email;
    private Boolean enabled;
    private GenderType genderType;
    private Date birthDay;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer age;
}
