package ua.com.alevel.network.facade;

import org.springframework.web.context.request.WebRequest;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.network.web.data.request.PostData;
import ua.com.alevel.network.web.data.response.PageData;
import ua.com.alevel.network.web.data.response.PostResponseData;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:33 PM
 */
public interface PostFacade {

    void create(PostData data);
    void update(PostData data, Integer id);
    void delete(Integer id);
    PageData<PostData> findAll(WebRequest request);
    PostResponseData findById(Integer id);
    void like(Integer id);
    void dislike(Integer id);
    void uploadFile(MultipartFile file, Integer postId);
}
