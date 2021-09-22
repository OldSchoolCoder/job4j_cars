package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car implements Model {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty
    private String brand;

    @JsonProperty
    private String type;

    @JsonProperty
    private Integer power;

    @Override
    public Integer getId() {
        return id;
    }

    public Car() {
    }

    public Car(String brand, String type, Integer power) {
        this.brand = brand;
        this.type = type;
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, type);
    }
}
