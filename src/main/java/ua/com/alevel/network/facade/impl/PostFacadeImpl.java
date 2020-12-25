package ua.com.alevel.network.facade.impl;

import org.apache.commons.collections.CollectionUtils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.network.facade.PostFacade;
import ua.com.alevel.network.persistence.entity.post.Post;
import ua.com.alevel.network.persistence.entity.post.Reaction;
import ua.com.alevel.network.persistence.entity.user.Personal;
import ua.com.alevel.network.service.post.PostService;
import ua.com.alevel.network.service.post.ReactionService;
import ua.com.alevel.network.service.user.PersonalService;
import ua.com.alevel.network.web.data.request.PostData;
import ua.com.alevel.network.web.data.response.PageData;
import ua.com.alevel.network.web.data.response.PostResponseData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:34 PM
 */

@Service
public class PostFacadeImpl implements PostFacade {

    private final PostService postService;
    private final ReactionService reactionService;
    private final PersonalService personalService;

    public PostFacadeImpl(PostService postService, ReactionService reactionService, PersonalService personalService) {
        this.postService = postService;
        this.reactionService = reactionService;
        this.personalService = personalService;
    }

    @Override
    public void create(PostData data) {
        Post post = new Post();
        post.setMessage(data.getMessage());
        post.setTitle(data.getTitle());
        postService.create(post);
    }

    @Override
    public void update(PostData data, Integer id) {
        Post post = postService.findById(id);
        post.setMessage(data.getMessage());
        post.setTitle(data.getTitle());
        postService.update(post);
    }

    @Override
    public void delete(Integer id) {
        postService.delete(id);
    }

    @Override
    public PageData<PostData> findAll(WebRequest request) {
        Page<Post> page = postService.findAll(request);
        PageData<PostData> data = new PageData<>();
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getNumber());
        data.setTotalElements(page.getTotalPages());
        data.setTotalPages(page.getTotalPages());
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<PostData> list = page.getContent().stream().map(PostData::new).collect(Collectors.toList());
            data.setItems(list);
        }
        return data;
    }

    @Override
    public PostResponseData findById(Integer id) {
        Post post = postService.findById(id);
        List<Reaction> likeReactionList = reactionService.findAllByPostIdAndLikeTrue(post.getId());
        List<Reaction> dislikeReactionList = reactionService.findAllByPostIdAndLikeFalse(post.getId());
        PostResponseData data = new PostResponseData(post);
        if (CollectionUtils.isNotEmpty(likeReactionList)) {
            List<Integer> ids = likeReactionList.stream().map(Reaction::getPersonalId).collect(Collectors.toList());
            generatePostResponseData(data, ids, null);
        }
        if (CollectionUtils.isNotEmpty(dislikeReactionList)) {
            List<Integer> ids = dislikeReactionList.stream().map(Reaction::getPersonalId).collect(Collectors.toList());
            generatePostResponseData(data, null, ids);
        }
        return data;
    }

    @Override
    public void like(Integer id) {
        postService.like(id);
    }

    @Override
    public void dislike(Integer id) {
        postService.dislike(id);
    }

    @Override
    public void uploadFile(MultipartFile file, Integer postId) {

    }

    private void generatePostResponseData(PostResponseData data, List<Integer> likeIds, List<Integer> dislikeIds) {
        List<Personal> personals;
        Map<Integer, String> map;
        if (likeIds != null) {
            personals = personalService.findAllByListId(likeIds);
            map = personals.stream().collect(Collectors.toMap(Personal::getId, Personal::getFullName));
            data.setLikes(map);
        }
        if (dislikeIds != null) {
            personals = personalService.findAllByListId(dislikeIds);
            map = personals.stream().collect(Collectors.toMap(Personal::getId, Personal::getFullName));
            data.setDislikes(map);
        }
    }
}
