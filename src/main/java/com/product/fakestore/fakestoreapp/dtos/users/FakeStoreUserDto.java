package com.product.fakestore.fakestoreapp.dtos.users;

import lombok.Getter;
import lombok.Setter;

/**
 * This Dto is used to return User Details from Various FakeStore User Apis.
 */
@Getter
@Setter
public class FakeStoreUserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private FakeStoreUserNameDto name;
    private FakeStoreUserAddressDto address;
}
