package com.product.fakestore.fakestoreapp.dtos.users;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FakeStoreUserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private FakeStoreUserNameDto name;
    private FakeStoreUserAddressDto address;
}
