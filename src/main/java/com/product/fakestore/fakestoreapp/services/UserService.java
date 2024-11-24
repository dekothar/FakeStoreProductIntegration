package com.product.fakestore.fakestoreapp.services;

import com.product.fakestore.fakestoreapp.models.user.User;

public interface UserService {

    User add(User user);

    User update(User user,Long userId);
}
