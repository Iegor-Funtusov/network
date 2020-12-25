package ua.com.alevel.network.web.data.response;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import ua.com.alevel.network.persistence.entity.post.Post;
import ua.com.alevel.network.web.data.request.PostData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 2:09 PM
 */

@Getter
@Setter
@ToString
public class PostResponseData extends PostData {

    private Map<Integer, String> likes;
    private Map<Integer, String> dislikes;

    public PostResponseData(Post post) {
        super(post);
        this.likes = new HashMap<>();
        this.dislikes = new HashMap<>();
    }
}
