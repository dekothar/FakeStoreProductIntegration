package com.product.fakestore.fakestoreapp.dtos.carts;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class FakeStoreCartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<FakeStoreProductDto> products = new ArrayList<>();

 }

