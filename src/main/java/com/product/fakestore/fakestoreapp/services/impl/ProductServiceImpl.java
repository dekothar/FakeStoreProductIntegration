package com.product.fakestore.fakestoreapp.services.impl;

import com.product.fakestore.fakestoreapp.client.FakeStoreClient;
import com.product.fakestore.fakestoreapp.dtos.products.FakeStoreProductRequestDto;
import com.product.fakestore.fakestoreapp.dtos.products.FakeStoreProductResponseDto;
import com.product.fakestore.fakestoreapp.models.product.Category;
import com.product.fakestore.fakestoreapp.models.product.Product;
import com.product.fakestore.fakestoreapp.services.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_URL="https://fakestoreapi.com/products";
    private static final String PRODUCT_BY_ID_URL="https://fakestoreapi.com/products/{productId}";
    private static final String CATEGORIES_URL="https://fakestoreapi.com/products/categories";
    private static final String PRODUCTS_BY_CATEGORY_TYPE="https://fakestoreapi.com/products/category/{categoryType}";

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        ResponseEntity<FakeStoreProductResponseDto []>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCTS_URL, HttpMethod.GET,null, FakeStoreProductResponseDto [].class);
        FakeStoreProductResponseDto [] responseDto=responseDtoResponseEntity.getBody();
        for(FakeStoreProductResponseDto fakeStoreProductResponseDto:responseDto){
            Product product = convertToProduct(fakeStoreProductResponseDto);
            products.add(product);

        }
        return  products;
    }

    @Override
    public Product getProductById(Long productId) {
        ResponseEntity<FakeStoreProductResponseDto>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCT_BY_ID_URL, HttpMethod.GET,null, FakeStoreProductResponseDto.class,productId);
        FakeStoreProductResponseDto responseDto=responseDtoResponseEntity.getBody();
        return convertToProduct(responseDto);
    }

    @Override
    public Product createProduct(String title, double price, String description, String category, String image) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto=convertToFakeStoreProduct(title,price,description,category,image);
        ResponseEntity<FakeStoreProductResponseDto>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCTS_URL, HttpMethod.POST,fakeStoreProductRequestDto, FakeStoreProductResponseDto.class);
        FakeStoreProductResponseDto responseDto=responseDtoResponseEntity.getBody();
        return convertToProduct(responseDto);
    }

    @Override
    public String deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductResponseDto>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCT_BY_ID_URL, HttpMethod.DELETE,null, FakeStoreProductResponseDto.class,productId);
        FakeStoreProductResponseDto responseDto=responseDtoResponseEntity.getBody();
        if(responseDto!=null){
            return "Product is removed Successfully from the Store";
        }
        return "There is no product exists with this productId";
    }

    @Override
    public List<Product> getProductInCategory(String categoryType) {

        List<Product> products = new ArrayList<>();
        ResponseEntity<FakeStoreProductResponseDto []>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCTS_BY_CATEGORY_TYPE, HttpMethod.GET,null, FakeStoreProductResponseDto [].class,categoryType);
        FakeStoreProductResponseDto [] responseDto=responseDtoResponseEntity.getBody();
        for(FakeStoreProductResponseDto fakeStoreProductResponseDto:responseDto){
            Product product = convertToProduct(fakeStoreProductResponseDto);
            products.add(product);

        }
        return  products;
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseEntity<String []>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(CATEGORIES_URL, HttpMethod.GET,null, String [].class);
        String [] responseDto=responseDtoResponseEntity.getBody();
        List<Category> categories = new ArrayList<>();
        for(String categoryType:responseDto){
            Category category=new Category();
            category.setTitle(categoryType);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public Product updateProduct(Long id, String title, double price, String description, String category, String image) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto=convertToFakeStoreProduct(title,price,description,category,image);
        fakeStoreProductRequestDto.setId(id);
        ResponseEntity<FakeStoreProductResponseDto>  responseDtoResponseEntity=fakeStoreClient.requestForEntity(PRODUCT_BY_ID_URL, HttpMethod.PUT,fakeStoreProductRequestDto, FakeStoreProductResponseDto.class);
        FakeStoreProductResponseDto responseDto=responseDtoResponseEntity.getBody();
        return convertToProduct(responseDto);
    }

    public Product convertToProduct(FakeStoreProductResponseDto fakeStoreProductResponseDto) {
        Product product = new Product();
        product.setId(fakeStoreProductResponseDto.getId());
        product.setTitle(fakeStoreProductResponseDto.getTitle());
        product.setPrice(fakeStoreProductResponseDto.getPrice());
        product.setDesc(fakeStoreProductResponseDto.getDescription());
        product.setImg(fakeStoreProductResponseDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductResponseDto.getCategory());
        product.setCategory(category);
        return product;
    }

    public FakeStoreProductRequestDto convertToFakeStoreProduct(String title, double price, String description, String category, String image) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle(title);
        fakeStoreProductRequestDto.setPrice(price);
        fakeStoreProductRequestDto.setDescription(description);
        fakeStoreProductRequestDto.setImage(image);
        fakeStoreProductRequestDto.setCategory(category);
        return fakeStoreProductRequestDto;
    }
}
