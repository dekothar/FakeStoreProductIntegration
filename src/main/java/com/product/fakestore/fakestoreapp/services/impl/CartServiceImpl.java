package com.product.fakestore.fakestoreapp.services.impl;

import com.product.fakestore.fakestoreapp.client.FakeStoreClient;
import com.product.fakestore.fakestoreapp.dtos.carts.FakeStoreCartDto;
import com.product.fakestore.fakestoreapp.models.cart.Cart;
import com.product.fakestore.fakestoreapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    public Cart getCartById(Long cartId) {
        FakeStoreCartDto fakeStoreCart=fakeStoreClient.getCartById(cartId);
        return from(fakeStoreCart);
    }

    public List<Cart> getCartByUserId(Long userId) {
        FakeStoreCartDto[] fakeStoreCart=fakeStoreClient.getCartsByUserId(userId);
        List<Cart> carts=new ArrayList<>();
        for(FakeStoreCartDto fakeStoreCart2:fakeStoreCart){
            Cart cart=from(fakeStoreCart2);
            carts.add(cart);
        }
        return carts;
    }

    public Cart deleteCartById(Long cartId) {
        FakeStoreCartDto fakeStoreCart=fakeStoreClient.deleteCartById(cartId);
        return from(fakeStoreCart);
    }

    public Cart updateCart(Long cartId,Cart request) {
        FakeStoreCartDto fakeStoreCart=fakeStoreClient.updateCart(cartId,from(request));
        fakeStoreCart.setId(cartId);
        return from(fakeStoreCart);
    }

    public Cart addCart(Cart request) {
        FakeStoreCartDto fakeStoreCart=fakeStoreClient.addCart(from(request));
        return from(fakeStoreCart);
    }

    private FakeStoreCartDto from(Cart cart) {
        FakeStoreCartDto fakeStoreCart = new FakeStoreCartDto();
        fakeStoreCart.setId(cart.getId());
        Instant instant = cart.getDate().toInstant();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        fakeStoreCart.setDate(formatter.format(instant));
        fakeStoreCart.setUserId(cart.getUserId());
        if(cart.getProducts() != null) {
            List<FakeStoreCartDto.FakeStoreProductDto> fakeStoreProducts = new ArrayList<>();
            for (Map.Entry<Long, Double> product : cart.getProducts().entrySet()) {
                Long productId = product.getKey();
                Double quantity = product.getValue();
                FakeStoreCartDto.FakeStoreProductDto fakeStoreProduct = new FakeStoreCartDto.FakeStoreProductDto();
                fakeStoreProduct.setProductId(productId);
                fakeStoreProduct.setQuantity(quantity);
                fakeStoreProducts.add(fakeStoreProduct);
            }
            fakeStoreCart.setProducts(fakeStoreProducts);
        }
        return fakeStoreCart;
    }

    private Cart from(FakeStoreCartDto fakeStoreCart) {
        Cart cart = new Cart();
        cart.setId(fakeStoreCart.getId());
        cart.setUserId(fakeStoreCart.getUserId());
        Instant instant = Instant.parse(fakeStoreCart.getDate());
        cart.setDate(Date.from(instant));
        if(fakeStoreCart.getProducts() != null) {
            Map<Long,Double> products = new HashMap<>();
            for (FakeStoreCartDto.FakeStoreProductDto fakeStoreProduct : fakeStoreCart.getProducts()) {
                products.put(fakeStoreProduct.getProductId(),fakeStoreProduct.getQuantity());
            }
            cart.setProducts(products);
        }
        return cart;
    }
}
