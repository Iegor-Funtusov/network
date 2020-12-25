package ua.com.alevel.network.facade;

import ua.com.alevel.network.web.data.request.AuthData;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 10:59 AM
 */
public interface RegistrationFacade {

    void registration(AuthData authData);
}
