package ua.com.alevel.network.service.user;

import ua.com.alevel.network.persistence.entity.user.Personal;

import java.util.List;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 10:24 AM
 */

public interface PersonalService extends UserService<Personal> {

    Personal findByEmail(String email);
    List<Personal> findAllByListId(List<Integer> ids);
}
