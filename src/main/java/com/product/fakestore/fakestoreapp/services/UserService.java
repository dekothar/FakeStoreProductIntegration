package com.product.fakestore.fakestoreapp.services;

import com.product.fakestore.fakestoreapp.models.user.User;

/**
 * This interface Implements the Functionality for User Details.
 */
public interface UserService {

    /**
     * This Method Implements add Functionality.
     *
     * @param user
     * @return User
     */
    User add(User user);

    /**
     * This Method Implements update Functionality.
     *
     * @param user
     * @param userId
     * @return User
     */
    User update(User user, Long userId);
}
