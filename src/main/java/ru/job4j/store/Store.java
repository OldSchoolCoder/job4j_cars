package ru.job4j.store;

import ru.job4j.model.Model;
import ru.job4j.model.Post;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;

public interface Store {

    List<Post> postsFromLastDay();

    List<Post> postsWithPhoto();

    List<Post> allPosts();

    List<Post> findByBrand(String brand);

    void close();

    Optional<User> findByEmail(String email);

    Optional<Post> findPostById(Integer id);

    void add(Model model);
}
