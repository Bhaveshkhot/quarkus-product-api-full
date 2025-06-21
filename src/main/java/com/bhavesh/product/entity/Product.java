package com.bhavesh.product.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

@Entity
public class Product extends PanacheEntity {

    @NotBlank
    public String name;

    public String description;

    @NotNull
    @PositiveOrZero
    public Double price;

    @NotNull
    @Min(0)
    public Integer quantity;
}