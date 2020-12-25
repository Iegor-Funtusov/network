package ua.com.alevel.network.service.post;

import ua.com.alevel.network.persistence.entity.post.Reaction;
import ua.com.alevel.network.service.CrudService;

import java.util.List;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:30 PM
 */
public interface ReactionService extends CrudService<Reaction> {

    void deleteByPostId(Integer postId);
    void like(Integer rostId, Integer personalId);
    void dislike(Integer rostId, Integer personalId);
    List<Reaction> findAllByPostIdAndLikeTrue(Integer postId);
    List<Reaction> findAllByPostIdAndLikeFalse(Integer postId);
}
