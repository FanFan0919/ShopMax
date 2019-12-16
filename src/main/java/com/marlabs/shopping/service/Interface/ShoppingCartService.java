package com.marlabs.shopping.service.Interface;

import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart getShoppingCart(Integer uid, Integer pid);

    public void addShoppingCart(ShoppingCart shoppingCart);

    public boolean deleteShoppingCart(Integer uid, Integer pid);

    public boolean updateShoppingCart(ShoppingCart shoppingCart);

    public List<ShoppingCart> getShoppingCartList(Integer uid);

    public List<ProductInCart> listProductInCart(Integer uid);
}
