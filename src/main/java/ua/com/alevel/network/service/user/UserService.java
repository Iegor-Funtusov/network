package ua.com.alevel.network.service.user;

import ua.com.alevel.network.persistence.entity.user.User;
import ua.com.alevel.network.service.CrudService;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:52 AM
 */

public interface UserService<U extends User> extends CrudService<U> { }
