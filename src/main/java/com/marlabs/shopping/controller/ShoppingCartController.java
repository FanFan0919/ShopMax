package com.marlabs.shopping.controller;

import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.entity.WishList;
import com.marlabs.shopping.service.Interface.ProductService;
import com.marlabs.shopping.service.Interface.ShoppingCartService;
import com.marlabs.shopping.service.Interface.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ShoppingCartController {
    @Resource
    private ProductService productService;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private WishListService wishListService;

    @RequestMapping(value = "/addShoppingCart",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoppingCart(Integer uid, Integer pid, Integer quantity){
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(uid, pid);
        if(shoppingCart == null){
            ShoppingCart tempCart = new ShoppingCart();
            tempCart.setUid(uid);
            tempCart.setPid(pid);
            tempCart.setQuantity(quantity);
            shoppingCartService.addShoppingCart(tempCart);
        }
        else{
            shoppingCart.setQuantity(shoppingCart.getQuantity()+quantity);
            shoppingCartService.updateShoppingCart(shoppingCart);
        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getAllShoppingCarts")
    @ResponseBody
    public ModelAndView getAllShoppingCarts(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        if (httpSession.getAttribute("currentUser") == null) {
            mv.setViewName("shoppingCart");
            return mv;
        }
        User user = (User) httpSession.getAttribute("currentUser");
        List<ProductInCart> productInCartList = shoppingCartService.listProductInCart(user.getUid());
        mv.addObject("productInCartList", productInCartList);
        mv.setViewName("shoppingCart");
        return mv;
    }

    @RequestMapping(value = "/deleteShoppingCart")
    @ResponseBody
    public Map<String,Object> deleteShoppingCart(Integer uid,Integer pid){
        shoppingCartService.deleteShoppingCart(uid, pid);
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/moveToWishList")
    @ResponseBody
    public Map<String,Object> moveToWishList(Integer uid,Integer pid){
        Map<String, Object> resultMap = new HashMap<String,Object>();
        if (uid == -1) {
            resultMap.put("result", "pleaseLogin");
            return resultMap;
        }
        ShoppingCart movingShoppingCart = shoppingCartService.getShoppingCart(uid, pid);
        WishList newList = new WishList();
        newList.setUid(uid);
        newList.setPid(pid);
        newList.setQuantity(movingShoppingCart.getQuantity());
        if (wishListService.getWishList(uid, pid) == null) {
            wishListService.addWishList(newList);
        } else {
            newList.setQuantity(wishListService.getWishList(uid, pid).getQuantity() + movingShoppingCart.getQuantity());
            wishListService.updateWishList(newList);
        }
        shoppingCartService.deleteShoppingCart(uid, pid);
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/initializeCart")
    @ResponseBody
    public ModelAndView initializeCart(){
        // check if uid=-1(previous anonymous user) still have cart record
        List<ShoppingCart> list = shoppingCartService.getShoppingCartList(-1);
        if (list.size() > 0) {
            for (ShoppingCart s : list) {
                shoppingCartService.deleteShoppingCart(s.getUid(), s.getPid());
            }
        }
        return new ModelAndView("redirect:/getAllProducts");
//        ModelAndView mv =  new ModelAndView();
//        mv.setViewName("getAllProducts");
//        return mv;
    }
}
