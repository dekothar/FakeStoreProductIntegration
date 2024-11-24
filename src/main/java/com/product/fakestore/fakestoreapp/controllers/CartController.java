package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.models.cart.Cart;
import com.product.fakestore.fakestoreapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId){
        return cartService.getCartById(cartId);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Long userId){
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable Long cartId, @RequestBody Cart cart){
        return cartService.updateCart(cartId,cart);
    }

    @DeleteMapping("/{cartId}")
    public Cart deleteCartById(@PathVariable Long cartId){
        return cartService.deleteCartById(cartId);
    }
}
