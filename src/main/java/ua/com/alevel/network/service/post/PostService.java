package ua.com.alevel.network.service.post;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.network.persistence.entity.post.Post;
import ua.com.alevel.network.service.CrudService;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:25 PM
 */
public interface PostService extends CrudService<Post> {

    void like(Integer id);
    void dislike(Integer id);
    void uploadFile(MultipartFile file, Integer postId);
}
