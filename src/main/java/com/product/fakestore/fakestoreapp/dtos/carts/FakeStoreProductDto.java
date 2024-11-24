package com.product.fakestore.fakestoreapp.dtos.carts;

import lombok.Getter;
import lombok.Setter;

/**
 * This Dto is used to return Cart Details of a Product from Various FakeStore Cart Apis.
 */
@Getter
@Setter
public class FakeStoreProductDto {

    private Long productId;
    private Double quantity;
}
