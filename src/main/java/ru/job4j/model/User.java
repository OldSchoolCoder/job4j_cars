package ru.job4j.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Model {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(posts, user.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(),
                getEmail(), getPassword(), posts);
    }
}

