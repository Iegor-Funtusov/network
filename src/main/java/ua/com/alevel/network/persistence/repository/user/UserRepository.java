package ua.com.alevel.network.persistence.repository.user;

import org.springframework.stereotype.Repository;

import ua.com.alevel.network.persistence.entity.user.User;
import ua.com.alevel.network.persistence.repository.AbstractRepository;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:46 AM
 */
@Repository
public interface UserRepository<U extends User> extends AbstractRepository<U> {

    U findByEmail(String email);
    boolean existsByEmail(String email);
}
