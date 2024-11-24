package com.product.fakestore.fakestoreapp.services;


import com.product.fakestore.fakestoreapp.models.product.Category;
import com.product.fakestore.fakestoreapp.models.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(final Long productId);

    Product createProduct(final String title, final double price, final String description, final String category, final String image);

    String deleteProduct(final Long id);

    List<Product> getProductInCategory(final String categoryType);

    List<Category> getAllCategories();

    Product updateProduct(final Long id, final String title, final double price, final String description, final String category, final String image);
}
