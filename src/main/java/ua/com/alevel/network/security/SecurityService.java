package ua.com.alevel.network.security;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:48 AM
 */

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
