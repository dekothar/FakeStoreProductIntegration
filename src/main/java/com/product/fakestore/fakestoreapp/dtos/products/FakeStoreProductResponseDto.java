package com.product.fakestore.fakestoreapp.dtos.products;

import com.product.fakestore.fakestoreapp.models.product.Category;
import com.product.fakestore.fakestoreapp.models.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FakeStoreProductResponseDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
