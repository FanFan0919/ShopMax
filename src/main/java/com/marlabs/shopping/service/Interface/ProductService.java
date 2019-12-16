package com.marlabs.shopping.service.Interface;

import com.marlabs.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    public Product getProduct(Integer pid);

    public Product getProduct(String name);

    public void addProduct(Product product);

    public List<Product> getProductsByType(Integer type);

    public List<Product> getAllProducts();
}
