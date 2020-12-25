package ua.com.alevel.network.persistence.entity.post;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.network.persistence.entity.AbstractEntity;
import ua.com.alevel.network.persistence.entity.user.Personal;

import javax.persistence.*;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 12:18 PM
 */

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Personal personal;

    public Post() {
        super();
    }
}
