package com.product.fakestore.fakestoreapp.models.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Category {

    private String title;
    private List<Product> products;

}
