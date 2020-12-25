package ua.com.alevel.network.persistence.repository.user;

import org.springframework.stereotype.Repository;

import ua.com.alevel.network.persistence.entity.user.Admin;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 9:45 AM
 */

@Repository
public interface AdminRepository extends UserRepository<Admin> { }
