package com.product.fakestore.fakestoreapp.dtos.users;

import lombok.Getter;
import lombok.Setter;

/**
 * This Dto is used to return Address Details from Various FakeStore User Apis.
 */
@Getter
@Setter
public class FakeStoreUserAddressDto {

    private String city;
    private String street;
    private Long number;
}
