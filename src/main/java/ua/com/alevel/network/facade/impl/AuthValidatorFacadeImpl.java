package ua.com.alevel.network.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.network.facade.AuthValidatorFacade;
import ua.com.alevel.network.service.user.PersonalService;
import ua.com.alevel.network.web.data.request.AuthData;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 11:10 AM
 */

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final PersonalService personalService;

    public AuthValidatorFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuthData data = (AuthData) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (data.getEmail().length() < 6 || data.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.authForm.email");
        }
        if (personalService.findByEmail(data.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (data.getPassword().length() < 8 || data.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }

        if (!data.getPasswordConfirm().equals(data.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
