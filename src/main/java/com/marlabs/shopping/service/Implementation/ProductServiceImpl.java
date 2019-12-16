package com.marlabs.shopping.service.Implementation;


import com.marlabs.shopping.dao.Interface.ProductDao;
import com.marlabs.shopping.entity.Product;
import com.marlabs.shopping.service.Interface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProduct(Integer pid) {
        return productDao.getProduct(pid);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> getProductsByType(Integer type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
