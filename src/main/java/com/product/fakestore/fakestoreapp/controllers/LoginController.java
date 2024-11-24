package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.dtos.login.FakeStoreLoginRequestDto;
import com.product.fakestore.fakestoreapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FakeStoreLoginRequestDto fakeStoreLoginRequestDto){
        MultiValueMap<String, String> hmap=authService.login(fakeStoreLoginRequestDto);
        ResponseEntity<String> result=null;
        if(hmap!=null){
            result=new ResponseEntity<>("login successful",hmap, HttpStatus.OK);
        }
        else{
            result=new ResponseEntity<>("login failed",null, HttpStatus.UNAUTHORIZED);
        }
        return result;
    }
}
