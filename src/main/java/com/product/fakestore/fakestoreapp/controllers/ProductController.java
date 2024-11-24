package com.product.fakestore.fakestoreapp.controllers;

import com.product.fakestore.fakestoreapp.models.product.Category;
import com.product.fakestore.fakestoreapp.models.product.Product;
import com.product.fakestore.fakestoreapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/category/{categoryType}")
    public List<Product> getProductsByCategoryType(@PathVariable String categoryType){
        return productService.getProductInCategory(categoryType);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.createProduct(product.getTitle(), product.getPrice(),product.getDesc(),product.getCategory().getTitle(), product.getImg());
    }

    @PutMapping("/{productId}")
    public Product addProduct(@RequestBody Product product,@PathVariable long productId){
        return productService.updateProduct(productId,product.getTitle(), product.getPrice(),product.getDesc(),product.getCategory().getTitle(), product.getImg());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        String message = productService.deleteProduct(productId);
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }
}
