package com.marlabs.shopping.dao.Interface;

import com.marlabs.shopping.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductDao {
    public Product getProduct(Integer pid);

    public Product getProduct(String name);

    public void addProduct(Product product);

    public List<Product> getProductsByType(Integer type);

    public List<Product> getAllProducts();
}
