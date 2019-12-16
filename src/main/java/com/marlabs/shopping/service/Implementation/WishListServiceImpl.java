package com.marlabs.shopping.service.Implementation;

import com.marlabs.shopping.dao.Implementation.WishListDaoImpl;
import com.marlabs.shopping.dao.Interface.WishListDao;
import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.entity.WishList;
import com.marlabs.shopping.service.Interface.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishListDao dao;
    @Override
    public WishList getWishList(Integer uid, Integer pid) {
        return dao.getWishList(uid, pid);
    }

    @Override
    public void addWishList(WishList wishList) {
        dao.addWishList(wishList);
    }

    @Override
    public boolean deleteWishList(Integer uid, Integer pid) {
        return dao.deleteWishList(uid, pid);
    }

    @Override
    public boolean updateWishList(WishList wishList) {
        return dao.updateWishList(wishList);
    }

    @Override
    public List<WishList> getWishListList(Integer uid) {
        return dao.getWishListList(uid);
    }

    @Override
    public List<ProductInCart> listProductInWishList(Integer uid) {
        return dao.listProductInWishList(uid);
    }
}
