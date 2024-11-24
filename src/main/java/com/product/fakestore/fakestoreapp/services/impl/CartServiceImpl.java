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

/**
 * This Class Performs Logic Integration of various cart related Apis Coming from FakeStore Cart Apis.
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
        fakeStoreCart.setId(cart.getId());
        Instant instant = cart.getDate().toInstant();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        fakeStoreCart.setDate(formatter.format(instant));
        fakeStoreCart.setUserId(cart.getUserId());
        if (cart.getProducts() != null) {
            List<FakeStoreProductDto> fakeStoreProducts = new ArrayList<>();
            for (Map.Entry<Long, Double> product : cart.getProducts().entrySet()) {
                Long productId = product.getKey();
                Double quantity = product.getValue();
                FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
                fakeStoreProduct.setProductId(productId);
                fakeStoreProduct.setQuantity(quantity);
                fakeStoreProducts.add(fakeStoreProduct);
            }
            fakeStoreCart.setProducts(fakeStoreProducts);
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
            Map<Long, Double> products = new HashMap<>();
            for (FakeStoreProductDto fakeStoreProduct : fakeStoreCart.getProducts()) {
                products.put(fakeStoreProduct.getProductId(), fakeStoreProduct.getQuantity());
            }
            cart.setProducts(products);
        }
        return cart;
    }
}
