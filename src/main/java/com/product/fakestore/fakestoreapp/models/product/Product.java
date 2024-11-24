package com.product.fakestore.fakestoreapp.models.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {

    private Long id;
    private String title;
    private String desc;
    private double price;
    private Category category;
    private String img;
}
