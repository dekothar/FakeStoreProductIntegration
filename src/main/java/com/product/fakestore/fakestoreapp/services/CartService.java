package com.product.fakestore.fakestoreapp.services;

import com.product.fakestore.fakestoreapp.models.cart.Cart;

import java.util.List;

public interface CartService {

    Cart getCartById(Long cartId);

    List<Cart> getCartByUserId(Long userId);

    Cart deleteCartById(Long cartId);

    Cart updateCart(Long cartId, Cart request);

    Cart addCart(Cart request);
}
