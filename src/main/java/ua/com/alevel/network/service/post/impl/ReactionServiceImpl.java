package ua.com.alevel.network.service.post.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.network.persistence.entity.post.Reaction;
import ua.com.alevel.network.persistence.repository.post.ReactionRepository;
import ua.com.alevel.network.service.post.ReactionService;

import java.util.List;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:31 PM
 */

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;

    public ReactionServiceImpl(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    public Page<Reaction> findAll(WebRequest request) {
        return null;
    }

    @Override
    public Reaction findById(Integer id) {
        return null;
    }

    @Override
    public void create(Reaction reaction) {

    }

    @Override
    public void update(Reaction reaction) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void deleteByPostId(Integer postId) {
        List<Reaction> reactions = reactionRepository.findAllByPostId(postId);
        if (CollectionUtils.isNotEmpty(reactions)) {
            reactionRepository.deleteAll(reactions);
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Integer postId, Integer personalId) {
        reactionProcess(postId, personalId, true);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Integer postId, Integer personalId) {
        reactionProcess(postId, personalId, false);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByPostIdAndLikeTrue(Integer postId) {
        return reactionRepository.findAllByPostIdAndLikeTrue(postId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByPostIdAndLikeFalse(Integer postId) {
        return reactionRepository.findAllByPostIdAndLikeFalse(postId);
    }

    private void reactionProcess(Integer postId, Integer personalId, boolean status) {
        Reaction reaction = reactionRepository.findByPostIdAndPersonalId(postId, personalId);
        if (reaction == null) {
            reaction = new Reaction();
            reaction.setPostId(postId);
            reaction.setPersonalId(personalId);
        }
        reaction.setLike(status);
        reactionRepository.save(reaction);
    }
}
