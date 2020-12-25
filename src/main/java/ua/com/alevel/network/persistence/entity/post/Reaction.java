package ua.com.alevel.network.persistence.entity.post;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.network.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:20 PM
 */

@Getter
@Setter
@Entity
@Table(name = "reactions")
public class Reaction extends AbstractEntity {

    @Column(name = "personal_id", nullable = false)
    private Integer personalId;

    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "like_post", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean like;

    public Reaction() {
        super();
        this.like = false;
    }
}
