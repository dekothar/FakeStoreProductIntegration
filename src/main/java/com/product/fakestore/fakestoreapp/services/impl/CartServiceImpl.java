package com.product.fakestore.fakestoreapp.services.impl;

import com.product.fakestore.fakestoreapp.client.FakeStoreClient;
import com.product.fakestore.fakestoreapp.dtos.carts.FakeStoreCartDto;
import com.product.fakestore.fakestoreapp.dtos.carts.FakeStoreProductDto;
import com.product.fakestore.fakestoreapp.models.cart.Cart;
import com.product.fakestore.fakestoreapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This Class Performs Logic Integration of Various Cart Related Apis Coming from FakeStore Cart Apis.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    /**
     * This Method Gets Cart Details for Particular CartId.
     *
     * @param cartId
     * @return Cart
     */
    public Cart getCartById(Long cartId) {
        FakeStoreCartDto fakeStoreCart = fakeStoreClient.getCartById(cartId);
        return convertFakeStoreCartDtoIntoCart(fakeStoreCart);
    }

    /**
     * This Method Gets Cart Details for Particular User.
     *
     * @param userId
     * @return List of Cart
     */
    public List<Cart> getCartByUserId(Long userId) {
        FakeStoreCartDto[] fakeStoreCart = fakeStoreClient.getCartsByUserId(userId);
        List<Cart> carts = new ArrayList<>();
        for (FakeStoreCartDto fakeStoreCart2 : fakeStoreCart) {
            Cart cart = convertFakeStoreCartDtoIntoCart(fakeStoreCart2);
            carts.add(cart);
        }
        return carts;
    }

    /**
     * This Method Removes Cart Details for Particular CartId Provided.
     *
     * @param cartId
     * @return Cart
     */
    public Cart deleteCartById(Long cartId) {
        FakeStoreCartDto fakeStoreCart = fakeStoreClient.deleteCartById(cartId);
        return convertFakeStoreCartDtoIntoCart(fakeStoreCart);
    }

    /**
     * This Method Updates Cart Details for Particular CartId Provided.
     *
     * @param cartId
     * @param cartRequest
     * @return Cart
     */
    public Cart updateCart(Long cartId, Cart cartRequest) {
        FakeStoreCartDto fakeStoreCart = fakeStoreClient.updateCart(cartId, convertCartDetailsIntoFakeStoreCartDto(cartRequest));
        fakeStoreCart.setId(cartId);
        return convertFakeStoreCartDtoIntoCart(fakeStoreCart);
    }

    /**
     * This Method Creates Cart Details.
     *
     * @param cartRequest
     * @return Cart
     */
    public Cart addCart(Cart cartRequest) {
        FakeStoreCartDto fakeStoreCart = fakeStoreClient.addCart(convertCartDetailsIntoFakeStoreCartDto(cartRequest));
        return convertFakeStoreCartDtoIntoCart(fakeStoreCart);
    }

    /**
     * This Helper Method used to Convert Cart Details into FaKeStoreCartDto.
     *
     * @param cart
     * @return FakeStoreCartDto
     */
    private FakeStoreCartDto convertCartDetailsIntoFakeStoreCartDto(Cart cart) {
        FakeStoreCartDto fakeStoreCart = new FakeStoreCartDto();

// Set the ID and UserID
        fakeStoreCart.setId(cart.getId());
        fakeStoreCart.setUserId(cart.getUserId());

// Convert the date
        if (cart.getDate() != null) {
            Instant instant = cart.getDate().toInstant();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
            fakeStoreCart.setDate(formatter.format(instant));
        }

// Convert products map to an array of FakeStoreProductDto
        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            FakeStoreProductDto[] fakeStoreProducts = cart.getProducts().entrySet().stream()
                    .map(entry -> {
                        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
                        fakeStoreProduct.setProductId(entry.getKey());
                        fakeStoreProduct.setQuantity(entry.getValue());
                        return fakeStoreProduct;
                    })
                    .toArray(FakeStoreProductDto[]::new); // Convert the stream to an array
            fakeStoreCart.setProducts(fakeStoreProducts);
        } else {
            fakeStoreCart.setProducts(new FakeStoreProductDto[0]); // Ensure products is never null
        }

        return fakeStoreCart;


    }

    /**
     * This Helper Method used to Convert FaKeStoreCartDto into Cart Details.
     *
     * @param fakeStoreCart
     * @return Cart
     */
    private Cart convertFakeStoreCartDtoIntoCart(FakeStoreCartDto fakeStoreCart) {
        Cart cart = new Cart();
        cart.setId(fakeStoreCart.getId());
        cart.setUserId(fakeStoreCart.getUserId());
        Instant instant = Instant.parse(fakeStoreCart.getDate());
        cart.setDate(Date.from(instant));
        if (fakeStoreCart.getProducts() != null) {
            Map<Long, Double> products = Arrays.stream(fakeStoreCart.getProducts())
                    .collect(Collectors.toMap(FakeStoreProductDto::getProductId, FakeStoreProductDto::getQuantity));
            cart.setProducts(products);
        } else {
            cart.setProducts(Collections.emptyMap()); // Ensure products is never null
        }
        return cart;
    }
}
