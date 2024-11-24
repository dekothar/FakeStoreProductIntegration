package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.dtos.users.UserRequestDto;
import com.product.fakestore.fakestoreapp.models.user.Address;
import com.product.fakestore.fakestoreapp.models.user.Name;
import com.product.fakestore.fakestoreapp.models.user.User;
import com.product.fakestore.fakestoreapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User add(@RequestBody UserRequestDto userRequestDto){
        return userService.add(from(userRequestDto));
    }

    @PutMapping("/{userId}")
    public User update(@RequestBody UserRequestDto userRequestDto,@PathVariable long userId){
        return userService.update(from(userRequestDto),userId);
    }

    private User from( UserRequestDto userRequestDto) {
        User user = new User();

        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        if(userRequestDto.getAddress() != null) {
            Name name = new Name();
            name.setFirstName(userRequestDto.getName().getFirstName());
            name.setLastName(userRequestDto.getName().getLastName());
            user.setName(name);
        }

        if(userRequestDto.getName() != null) {
            Address address = new Address();
            address.setCity(userRequestDto.getAddress().getCity());
            address.setStreet(userRequestDto.getAddress().getStreet());
            address.setNumber(userRequestDto.getAddress().getNumber());
            user.setAddress(address);
        }

        return user;
    }
}
