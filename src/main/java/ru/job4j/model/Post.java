package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();
    private Boolean sale;
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(description, post.description) &&
                Objects.equals(car, post.car) &&
                Objects.equals(photos, post.photos) &&
                Objects.equals(sale, post.sale) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, car,
                photos, sale, author);
    }
}
