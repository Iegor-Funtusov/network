package ua.com.alevel.network.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.alevel.network.persistence.entity.user.Personal;
import ua.com.alevel.network.persistence.repository.user.PersonalRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:49 AM
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonalRepository personalRepository;

    public UserDetailsServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Personal personal = personalRepository.findByEmail(email);
        if (personal == null) {
            throw new UsernameNotFoundException("user not found by " + email);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(personal.getRoleType().name()));

        return new org.springframework.security.core.userdetails.User(
                personal.getEmail(),
                personal.getPassword(),
                personal.getEnabled(),
                true,
                true,
                true, grantedAuthorities);
    }
}
