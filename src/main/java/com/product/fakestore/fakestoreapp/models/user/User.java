package com.product.fakestore.fakestoreapp.models.user;

import lombok.Getter;
import lombok.Setter;

/**
 * This User Model is used to return User Details.
 */
@Getter
@Setter
public class User {

    private Long id;
    private String email;
    private String password;
    private String username;
    private Name name;
    private Address address;
}
