package com.product.fakestore.fakestoreapp.models.user;

import lombok.Getter;
import lombok.Setter;

/**
 * This Address Model is used to return Address Details of a User.
 */
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private Long number;
}
