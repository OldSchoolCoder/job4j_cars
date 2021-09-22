package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post implements Model {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty
    private String description;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @JsonProperty
    private Boolean sale;

    @JsonProperty
    @ManyToOne
    private User author;

    @JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Override
    public Integer getId() {
        return id;
    }

    public Post() {
    }

    public User getAuthor() {
        return author;
    }

    public Post(Integer id, String description, Car car, List<Photo> photos,
                Boolean sale, User author, Date created) {
        this.id = id;
        this.description = description;
        this.car = car;
        this.photos = photos;
        this.sale = sale;
        this.author = author;
        this.created = created;
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
