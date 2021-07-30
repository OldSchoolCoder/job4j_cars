package ru.job4j.store;

import ru.job4j.model.Post;

import java.util.List;

public interface Store {
    List<Post> postsFromLastDay();

    List<Post> postsWithPhoto();

    List<Post> findByBrand(String brand);

    void close();
}
