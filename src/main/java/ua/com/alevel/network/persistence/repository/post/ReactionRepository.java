package ua.com.alevel.network.persistence.repository.post;

import org.springframework.stereotype.Repository;

import ua.com.alevel.network.persistence.entity.post.Reaction;
import ua.com.alevel.network.persistence.repository.AbstractRepository;

import java.util.List;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:22 PM
 */

@Repository
public interface ReactionRepository extends AbstractRepository<Reaction> {

    List<Reaction> findAllByPostId(Integer id);
    List<Reaction> findAllByPostIdAndLikeTrue(Integer id);
    List<Reaction> findAllByPostIdAndLikeFalse(Integer id);
    Reaction findByPostIdAndPersonalId(Integer postId, Integer personalId);
}
