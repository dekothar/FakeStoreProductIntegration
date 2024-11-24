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

/**
 * This Class Performs Logic Integration of various User Related Apis Coming from FakeStore User Details Apis.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String UPDATE_USER = "https://fakestoreapi.com/users/{userId}";
    private static final String ADD_USER = "https://fakestoreapi.com/users";

    @Autowired
    private FakeStoreClient fakeStoreClient;

    /**
     * This Method Add User Details.
     *
     * @param user
     * @return User
     */
    @Override
    public User add(User user) {
        FakeStoreUserDto userdto = convertUserDetailsIntoFakeStoreUserDto(user);
        ResponseEntity<FakeStoreUserCreatedResponseDto> response = fakeStoreClient.requestForEntity(ADD_USER, HttpMethod.POST, userdto, FakeStoreUserCreatedResponseDto.class);
        FakeStoreUserCreatedResponseDto res = response.getBody();
        user.setId(res.getId());
        return user;
    }

    /**
     * This Method Updates User Details for Particular UserId Provided.
     *
     * @param user
     * @param userId
     * @return User
     */
    @Override
    public User update(User user, Long userId) {
        FakeStoreUserDto fakeStoreUserRequestDtoInput = convertUserDetailsIntoFakeStoreUserDto(user);
        ResponseEntity<FakeStoreUserDto> response = fakeStoreClient.requestForEntity(UPDATE_USER, HttpMethod.PUT, fakeStoreUserRequestDtoInput, FakeStoreUserDto.class, userId);
        return convertFakeStoreUserDtoIntoUserDetails(response.getBody());
    }

    /**
     * This Helper Method used to Convert FakeStoreUserDto into User Details.
     *
     * @param fakeStoreUserRequestDto
     * @return User
     */
    private User convertFakeStoreUserDtoIntoUserDetails(FakeStoreUserDto fakeStoreUserRequestDto) {
        User user = new User();

        user.setUsername(fakeStoreUserRequestDto.getUsername());
        user.setPassword(fakeStoreUserRequestDto.getPassword());
        user.setEmail(fakeStoreUserRequestDto.getEmail());
        if (fakeStoreUserRequestDto.getAddress() != null) {
            Name name = new Name();
            name.setFirstName(fakeStoreUserRequestDto.getName().getFirstName());
            name.setLastName(fakeStoreUserRequestDto.getName().getLastName());
            user.setName(name);
        }

        if (fakeStoreUserRequestDto.getName() != null) {
            Address address = new Address();
            address.setCity(fakeStoreUserRequestDto.getAddress().getCity());
            address.setStreet(fakeStoreUserRequestDto.getAddress().getStreet());
            address.setNumber(fakeStoreUserRequestDto.getAddress().getNumber());
            user.setAddress(address);
        }

        return user;
    }

    /**
     * This Helper Method used to Convert User Details into FakeStoreUserDto.
     *
     * @param user
     * @return FakeStoreUserDto
     */
    private FakeStoreUserDto convertUserDetailsIntoFakeStoreUserDto(User user) {
        FakeStoreUserDto fakeStoreUserDto = new FakeStoreUserDto();
        fakeStoreUserDto.setUsername(user.getUsername());
        fakeStoreUserDto.setPassword(user.getPassword());
        fakeStoreUserDto.setEmail(user.getEmail());
        if (user.getAddress() != null) {
            FakeStoreUserNameDto name = new FakeStoreUserNameDto();
            name.setFirstName(user.getName().getFirstName());
            name.setLastName(user.getName().getLastName());
            fakeStoreUserDto.setName(name);
        }
        if (user.getName() != null) {
            FakeStoreUserAddressDto address = new FakeStoreUserAddressDto();
            address.setCity(user.getAddress().getCity());
            address.setStreet(user.getAddress().getStreet());
            address.setNumber(user.getAddress().getNumber());
            fakeStoreUserDto.setAddress(address);
        }
        return fakeStoreUserDto;
    }

}
