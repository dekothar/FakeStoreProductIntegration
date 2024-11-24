package com.product.fakestore.fakestoreapp.models.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * This Cart Model is used to return Cart Details from Various FakeStore Cart Apis.
 */
@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private Date date;
    private Map<Long, Double> products = new HashMap<>();
}
