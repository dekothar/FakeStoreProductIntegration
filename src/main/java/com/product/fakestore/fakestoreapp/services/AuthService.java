package com.product.fakestore.fakestoreapp.services;

import com.product.fakestore.fakestoreapp.dtos.login.FakeStoreLoginRequestDto;
import org.springframework.util.MultiValueMap;

public interface AuthService {
    MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto);
}
