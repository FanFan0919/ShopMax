package com.marlabs.shopping.controller;

import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.entity.WishList;
import com.marlabs.shopping.service.Interface.ProductService;
import com.marlabs.shopping.service.Interface.ShoppingCartService;
import com.marlabs.shopping.service.Interface.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WishListController {
    @Resource
    private WishListService wishListService;
    @Resource
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/addWishList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addWishList(Integer uid, Integer pid, Integer quantity){
        WishList wishList = wishListService.getWishList(uid, pid);
        if(wishList == null){
            WishList tempWishList = new WishList();
            tempWishList.setUid(uid);
            tempWishList.setPid(pid);
            tempWishList.setQuantity(quantity);
            wishListService.addWishList(tempWishList);
        }
        else{
            wishList.setQuantity(wishList.getQuantity()+quantity);
            wishListService.updateWishList(wishList);
        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getAllWishLists")
    @ResponseBody
    public ModelAndView getAllWishLists(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        if (httpSession.getAttribute("currentUser") == null) {
            mv.setViewName("wishList");
            return mv;
        }
        User user = (User) httpSession.getAttribute("currentUser");
        List<ProductInCart> productInWishList = wishListService.listProductInWishList(user.getUid());
        mv.addObject("productInWishList", productInWishList);
        mv.setViewName("wishList");
        return mv;
    }

    @RequestMapping(value = "/deleteWishList")
    @ResponseBody
    public Map<String,Object> deleteWishList(Integer uid,Integer pid){
        wishListService.deleteWishList(uid, pid);
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/moveToCart")
    @ResponseBody
    public Map<String,Object> moveToCart(Integer uid,Integer pid){
        WishList movingWishList = wishListService.getWishList(uid, pid);
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUid(uid);
        newCart.setPid(pid);
        newCart.setQuantity(movingWishList.getQuantity());
        if (shoppingCartService.getShoppingCart(uid, pid) == null) {
            shoppingCartService.addShoppingCart(newCart);
        } else {
            newCart.setQuantity(shoppingCartService.getShoppingCart(uid, pid).getQuantity() + movingWishList.getQuantity());
            shoppingCartService.updateShoppingCart(newCart);
        }

        wishListService.deleteWishList(uid, pid);
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }


    @RequestMapping(value = "/wishListAddOne")
    @ResponseBody
    // 1. no login, update session
    // 2. already login, update database
    public void addOne(Integer uid, Integer pid, HttpSession httpSession) {
        if (uid == -1) {
            List<ProductInCart> productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
            for (ProductInCart p : productInCartList) {
                if (p.getPid() == pid) {
                    p.setQuantity(p.getQuantity() + 1);
                }
            }
        } else {
            WishList wishList = wishListService.getWishList(uid, pid);
            wishList.setQuantity(wishList.getQuantity() + 1);
            wishListService.updateWishList(wishList);
        }
    }

    @RequestMapping(value = "/wishListRemoveOne")
    @ResponseBody
    // 1. no login, update session
    // 2. already login, update database
    public void removeOne(Integer uid, Integer pid, HttpSession httpSession) {
        if (uid == -1) {
            List<ProductInCart> productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
            for (ProductInCart p : productInCartList) {
                if (p.getPid() == pid) {
                    p.setQuantity(p.getQuantity() - 1);
                }
            }
        } else {
            WishList wishList = wishListService.getWishList(uid, pid);
            wishList.setQuantity(wishList.getQuantity() - 1);
            wishListService.updateWishList(wishList);
        }
    }

}
