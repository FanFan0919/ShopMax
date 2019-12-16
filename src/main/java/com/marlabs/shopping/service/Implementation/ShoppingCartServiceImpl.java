package com.marlabs.shopping.service.Implementation;

import com.marlabs.shopping.dao.Interface.ShoppingCartDao;
import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.service.Interface.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao dao;
    @Override
    public ShoppingCart getShoppingCart(Integer uid, Integer pid) {
        return dao.getShoppingCart(uid, pid);
    }

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        dao.addShoppingCart(shoppingCart);
    }

    @Override
    public boolean deleteShoppingCart(Integer uid, Integer pid) {
        return dao.deleteShoppingCart(uid, pid);
    }

    @Override
    public boolean updateShoppingCart(ShoppingCart shoppingCart) {
        return dao.updateShoppingCart(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getShoppingCartList(Integer uid) {
        return dao.getShoppingCartList(uid);
    }

    @Override
    public List<ProductInCart> listProductInCart(Integer uid) {
        return dao.listProductInCart(uid);
    }
}
