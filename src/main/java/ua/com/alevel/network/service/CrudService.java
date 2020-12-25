package ua.com.alevel.network.service;

import org.springframework.data.domain.Page;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.network.persistence.entity.AbstractEntity;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 10:12 AM
 */

public interface CrudService<AE extends AbstractEntity> {

    Page<AE> findAll(WebRequest request);
    AE findById(Integer id);
    void create(AE ae);
    void update(AE ae);
    void delete(Integer id);
}
