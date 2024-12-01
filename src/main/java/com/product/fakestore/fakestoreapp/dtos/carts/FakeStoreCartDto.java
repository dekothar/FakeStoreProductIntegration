package com.product.fakestore.fakestoreapp.dtos.carts;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * This Dto is used to return Cart Details from Various FakeStore Cart Apis.
 */
@Data
public class FakeStoreCartDto {
    private Long id;
    private Long userId;
    private String date;
    private FakeStoreProductDto [] products;

}

