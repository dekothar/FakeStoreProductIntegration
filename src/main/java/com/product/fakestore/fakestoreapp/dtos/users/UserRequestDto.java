package com.product.fakestore.fakestoreapp.dtos.users;

import com.product.fakestore.fakestoreapp.models.user.Address;
import com.product.fakestore.fakestoreapp.models.user.Name;
import lombok.Getter;
import lombok.Setter;

/**
 * This Dto is used to return User Details.
 */

@Getter
@Setter
public class UserRequestDto {

    private String email;
    private String password;
    private String username;
    private Name name;
    private Address address;
}
