package com.marlabs.shopping.dao.Interface;

import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.entity.WishList;

import java.util.List;

public interface WishListDao {
    public WishList getWishList(Integer uid, Integer pid);

    public void addWishList(WishList wishList);

    public boolean deleteWishList(Integer uid, Integer pid);

    public boolean updateWishList(WishList wishList);

    public List<WishList> getWishListList(Integer uid);

    public List<ProductInCart> listProductInWishList(Integer uid);
}
