package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.dtos.users.UserRequestDto;
import com.product.fakestore.fakestoreapp.models.user.Address;
import com.product.fakestore.fakestoreapp.models.user.Name;
import com.product.fakestore.fakestoreapp.models.user.User;
import com.product.fakestore.fakestoreapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This CartController Handles Various APi Operations Performed On Users from Fake Store Api of User Details.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This Api Returns the Added User Details.
     *
     * @param userRequestDto
     * @return User
     */
    @PostMapping
    public User add(@RequestBody UserRequestDto userRequestDto) {
        return userService.add(convertUserRequestDtoIntoUser(userRequestDto));
    }

    /**
     * This Api Returns the User Details for the Particular UserId.
     *
     * @return User
     * @Param userId
     */
    @GetMapping("/{userId}")
    public User getUserDetails(@PathVariable long userId) {
        return userService.getUserDetails(userId);
    }

    /**
     * This Api Returns All User Details.
     *
     * @return User
     * @Param userId
     */
    @GetMapping("")
    public List<User> getAllUserDetails() {
        return userService.getAllUsers();
    }

    /**
     * This Api Returns the Updated User Details for the Particular UserId.
     *
     * @param userRequestDto
     * @return User
     * @Param userId
     */
    @PutMapping("/{userId}")
    public User update(@RequestBody UserRequestDto userRequestDto, @PathVariable long userId) {
        return userService.update(convertUserRequestDtoIntoUser(userRequestDto), userId);
    }

    /**
     * This Helper Method Used to Convert UserRequestDto into User.
     *
     * @param userRequestDto
     * @return User.
     */
    private User convertUserRequestDtoIntoUser(UserRequestDto userRequestDto) {
        User user = new User();

        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        if (userRequestDto.getAddress() != null) {
            Name name = new Name();
            name.setFirstName(userRequestDto.getName().getFirstName());
            name.setLastName(userRequestDto.getName().getLastName());
            user.setName(name);
        }

        if (userRequestDto.getName() != null) {
            Address address = new Address();
            address.setCity(userRequestDto.getAddress().getCity());
            address.setStreet(userRequestDto.getAddress().getStreet());
            address.setNumber(userRequestDto.getAddress().getNumber());
            user.setAddress(address);
        }

        return user;
    }
}
