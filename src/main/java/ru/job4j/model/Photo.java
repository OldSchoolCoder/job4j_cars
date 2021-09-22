package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photo implements Model {
    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public Photo() {
    }

    public Photo(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(name, photo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
