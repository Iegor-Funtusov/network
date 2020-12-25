package ua.com.alevel.network.web.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ua.com.alevel.network.persistence.entity.post.Post;

import java.util.Date;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:33 PM
 */

@Getter
@Setter
@NoArgsConstructor
public class PostData {

    private Integer id;
    private String title;
    private String message;
    private Date created;

    public PostData(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.created = post.getCreated();
    }
}
