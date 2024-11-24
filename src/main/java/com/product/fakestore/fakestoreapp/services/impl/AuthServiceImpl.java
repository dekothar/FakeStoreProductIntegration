package com.product.fakestore.fakestoreapp.services.impl;

import com.product.fakestore.fakestoreapp.dtos.login.FakeStoreLoginRequestDto;
import com.product.fakestore.fakestoreapp.dtos.login.FakeStoreLoginResponseDto;
import com.product.fakestore.fakestoreapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String AUTH_LOGIN="https://fakestoreapi.com/auth/login";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto) {

        try{
            MultiValueMap<String, String> hmap=new LinkedMultiValueMap<>();
            ResponseEntity<FakeStoreLoginResponseDto> response=restTemplateBuilder.build().postForEntity(AUTH_LOGIN, fakeStoreLoginRequestDto, FakeStoreLoginResponseDto.class);
                String token=response.getBody().getToken();
                hmap.add(HttpHeaders.SET_COOKIE,token);
                return hmap;

            }catch(HttpClientErrorException e){
                return null;
            }
    }
}
