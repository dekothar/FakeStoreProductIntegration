package com.product.fakestore.fakestoreapp.services;

import com.product.fakestore.fakestoreapp.models.cart.Cart;

import java.util.List;

/**
 * This interface Implements the Functionality for Cart Details.
 */
public interface CartService {

    /**
     * This Method Implements getCartById Functionality.
     *
     * @param cartId
     * @return Cart
     */
    Cart getCartById(Long cartId);

    /**
     * This Method Implements getCartByUserId Functionality.
     *
     * @param userId
     * @return List of Cart
     */
    List<Cart> getCartByUserId(Long userId);

    /**
     * This Method Implements deleteCartById Functionality.
     *
     * @param cartId
     * @return Cart
     */
    Cart deleteCartById(Long cartId);

    /**
     * This Method Implements updateCart Functionality.
     *
     * @param cartId
     * @return Cart
     * @Param cartRequest
     */
    Cart updateCart(Long cartId, Cart cartRequest);

    /**
     * This Method Implements addCart Functionality.
     *
     * @return Cart
     * @Param cartRequest
     */
    Cart addCart(Cart cartRequest);
}
