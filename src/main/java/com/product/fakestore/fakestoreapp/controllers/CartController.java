package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.models.cart.Cart;
import com.product.fakestore.fakestoreapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This CartController Handles Various APi Operations Performed On Cart from Fake Store Api of Carts.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * This Api Returns the Cart Details for the cartId to User.
     *
     * @param cartId
     * @return Cart
     */
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    /**
     * This Api Returns the Cart Details for the Particular User.
     *
     * @param userId
     * @return List of Cart
     */
    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    /**
     * This Api Returns the Added Cart Details.
     *
     * @param cart
     * @return Cart
     */
    @PostMapping("")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    /**
     * This Api Returns the Updated Cart Details for the Particular CartId.
     *
     * @param cartId
     * @return Cart
     * @Param cart
     */
    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable Long cartId, @RequestBody Cart cart) {
        return cartService.updateCart(cartId, cart);
    }

    /**
     * This Api Returns the Cart Details Removed from the System for Particular CartId.
     *
     * @param cartId
     * @return Cart
     */
    @DeleteMapping("/{cartId}")
    public Cart deleteCartById(@PathVariable Long cartId) {
        return cartService.deleteCartById(cartId);
    }
}
