package ua.com.alevel.network.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import ua.com.alevel.network.persistence.type.RoleType;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 1:56 PM
 */

public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUsername() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername();
    }

    public static RoleType getRoleType() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        GrantedAuthority authority = authorities.stream().findFirst().get();
        return RoleType.valueOf(authority.getAuthority());
    }

    public boolean hasRole(Authentication authentication, String role) {
        if (authentication != null) {
            AtomicBoolean result = new AtomicBoolean(false);
            authentication.getAuthorities().forEach(authority -> result.set(authority.getAuthority().equalsIgnoreCase(role)));
            return result.get();
        }
        return false;
    }
}

