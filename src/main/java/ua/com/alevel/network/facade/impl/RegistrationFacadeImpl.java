package ua.com.alevel.network.facade.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.network.facade.RegistrationFacade;
import ua.com.alevel.network.persistence.entity.user.Personal;
import ua.com.alevel.network.service.user.PersonalService;
import ua.com.alevel.network.web.data.request.AuthData;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 10:59 AM
 */

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthData authData) {
        Personal personal = new Personal();
        personal.setEmail(authData.getEmail());
        personal.setPassword(authData.getPassword());
        personalService.create(personal);
    }
}
