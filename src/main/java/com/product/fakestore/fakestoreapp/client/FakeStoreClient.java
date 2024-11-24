package com.product.fakestore.fakestoreapp.client;

import com.product.fakestore.fakestoreapp.dtos.carts.FakeStoreCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

@Component
public class FakeStoreClient {

    private static final String GET_OR_DELETE_OR_UPDATE_CART_BY_ID ="https://fakestoreapi.com/carts/{cartId}";
    private static final String ADD_CART ="https://fakestoreapi.com/carts";
    private static final String GET_CARTS_BY_USER_ID ="https://fakestoreapi.com/carts/user/{userId}";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCartDto getCartById(Long cartId) {
        ResponseEntity<FakeStoreCartDto> response=requestForEntity(GET_OR_DELETE_OR_UPDATE_CART_BY_ID, HttpMethod.GET, null, FakeStoreCartDto.class,cartId);
        return response.getBody();
    }

    public FakeStoreCartDto[] getCartsByUserId(Long userId) {
        ResponseEntity<FakeStoreCartDto[]> response=requestForEntity(GET_CARTS_BY_USER_ID, HttpMethod.GET, null, FakeStoreCartDto[].class,userId);
        return response.getBody();
    }

    public FakeStoreCartDto deleteCartById(Long cartId) {
        ResponseEntity<FakeStoreCartDto> response=requestForEntity(GET_OR_DELETE_OR_UPDATE_CART_BY_ID, HttpMethod.DELETE, null, FakeStoreCartDto.class,cartId);
        return response.getBody();
    }

    public FakeStoreCartDto updateCart(Long cartId, FakeStoreCartDto request) {
        ResponseEntity<FakeStoreCartDto> response=requestForEntity(GET_OR_DELETE_OR_UPDATE_CART_BY_ID, HttpMethod.PUT, request, FakeStoreCartDto.class,cartId);
        return response.getBody();
    }

    public FakeStoreCartDto addCart(FakeStoreCartDto request) {
        ResponseEntity<FakeStoreCartDto> response=requestForEntity(ADD_CART, HttpMethod.POST, request, FakeStoreCartDto.class);
        return response.getBody();
    }


    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpmethod,@Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {

        RequestCallback requestCallback = restTemplateBuilder.build().httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplateBuilder.build().responseEntityExtractor(responseType);
        return restTemplateBuilder.build().execute(url, httpmethod, requestCallback, responseExtractor, uriVariables);
    }
}
