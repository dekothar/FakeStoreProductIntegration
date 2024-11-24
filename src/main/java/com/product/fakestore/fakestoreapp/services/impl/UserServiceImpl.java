package com.product.fakestore.fakestoreapp.services.impl;

import com.product.fakestore.fakestoreapp.client.FakeStoreClient;
import com.product.fakestore.fakestoreapp.dtos.users.FakeStoreUserAddressDto;
import com.product.fakestore.fakestoreapp.dtos.users.FakeStoreUserCreatedResponseDto;
import com.product.fakestore.fakestoreapp.dtos.users.FakeStoreUserDto;
import com.product.fakestore.fakestoreapp.dtos.users.FakeStoreUserNameDto;
import com.product.fakestore.fakestoreapp.models.user.User;
import com.product.fakestore.fakestoreapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.product.fakestore.fakestoreapp.models.user.Name;
import com.product.fakestore.fakestoreapp.models.user.Address;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String UPDATE_USER="https://fakestoreapi.com/users/{userId}";
    private static final String ADD_USER="https://fakestoreapi.com/users";

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public User add(User user) {
        FakeStoreUserDto userdto=from(user);
        ResponseEntity<FakeStoreUserCreatedResponseDto> response=fakeStoreClient.requestForEntity(ADD_USER, HttpMethod.POST,userdto, FakeStoreUserCreatedResponseDto.class);
        FakeStoreUserCreatedResponseDto res=response.getBody();
        user.setId(res.getId());
        return user;
    }

    @Override
    public User update(User user, Long userId) {
        FakeStoreUserDto fakeStoreUserRequestDtoInput = from(user);
        ResponseEntity<FakeStoreUserDto> response=fakeStoreClient.requestForEntity(UPDATE_USER, HttpMethod.PUT,fakeStoreUserRequestDtoInput, FakeStoreUserDto.class,userId);
        return from(response.getBody());
    }

    private User from(FakeStoreUserDto fakeStoreUserRequestDto) {
        User user = new User();

        user.setUsername(fakeStoreUserRequestDto.getUsername());
        user.setPassword(fakeStoreUserRequestDto.getPassword());
        user.setEmail(fakeStoreUserRequestDto.getEmail());
        if(fakeStoreUserRequestDto.getAddress() != null) {
            Name name = new Name();
            name.setFirstName(fakeStoreUserRequestDto.getName().getFirstName());
            name.setLastName(fakeStoreUserRequestDto.getName().getLastname());
            user.setName(name);
        }

        if(fakeStoreUserRequestDto.getName() != null) {
            Address address = new Address();
            address.setCity(fakeStoreUserRequestDto.getAddress().getCity());
            address.setStreet(fakeStoreUserRequestDto.getAddress().getStreet());
            address.setNumber(fakeStoreUserRequestDto.getAddress().getNumber());
            user.setAddress(address);
        }

        return user;
    }

    private FakeStoreUserDto from(User user) {
        FakeStoreUserDto fakeStoreUserDto = new FakeStoreUserDto();
        fakeStoreUserDto.setUsername(user.getUsername());
        fakeStoreUserDto.setPassword(user.getPassword());
        fakeStoreUserDto.setEmail(user.getEmail());
        if(user.getAddress() != null) {
            FakeStoreUserNameDto name = new FakeStoreUserNameDto();
            name.setFirstName(user.getName().getFirstName());
            name.setLastname(user.getName().getLastName());
            fakeStoreUserDto.setName(name);
        }
        if(user.getName() != null) {
            FakeStoreUserAddressDto address = new FakeStoreUserAddressDto();
            address.setCity(user.getAddress().getCity());
            address.setStreet(user.getAddress().getStreet());
            address.setNumber(user.getAddress().getNumber());
            fakeStoreUserDto.setAddress(address);
        }
        return fakeStoreUserDto;
    }

}
