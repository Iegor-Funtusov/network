package ua.com.alevel.network.persistence.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ua.com.alevel.network.persistence.entity.post.Post;
import ua.com.alevel.network.persistence.entity.user.Personal;
import ua.com.alevel.network.persistence.repository.AbstractRepository;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:21 PM
 */

@Repository
public interface PostRepository extends AbstractRepository<Post> {

    Page<Post> findAllByPersonal(Personal personal, Pageable pageable);
    Page<Post> findAllByPersonalIsNot(Personal personal, Pageable pageable);
}
