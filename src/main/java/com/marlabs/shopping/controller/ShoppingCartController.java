package com.marlabs.shopping.controller;

import com.marlabs.shopping.entity.*;
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
    private ShoppingCartService shoppingCartService;
    @Resource
    private WishListService wishListService;
    @Resource
    private ProductService productService;


    @RequestMapping(value = "/addShoppingCart")
    @ResponseBody
    // 1. no login, save to session
    // 2. already login, save to database
    public Map<String,Object> addShoppingCart(Integer uid, Integer pid, Integer quantity, HttpSession httpSession){
        // 1. no login, save to session
        if(uid == -1) {
            Product p = productService.getProduct(pid);
            ProductInCart productInCart = new ProductInCart(pid, p.getName(), p.getPrice(), quantity, p.getImgUrl());
            List<ProductInCart> productInCartList = null;
            // if there is no product list in session, add a new one
            // if there is a product list in session, get it and update
            if (httpSession.getAttribute("productInCartList") == null) {
                productInCartList = new ArrayList<>();
            } else {
                productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
            }

            // if there is already this product in list, merge it
            boolean flag = true;
            for (ProductInCart oldPic : productInCartList) {
                // if there is a match
                if (oldPic.getPid() == pid) {
                    oldPic.setQuantity(oldPic.getQuantity() + quantity);
                    flag = false;
                    break;
                }
            }
            // if no match item, don't need to merge, just add item to list
            if (flag) {
                productInCartList.add(productInCart);
            };

            httpSession.setAttribute("productInCartList", productInCartList);
        } else {
            // 2. already login, save to database
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(uid, pid);
            if(shoppingCart == null){
                ShoppingCart tempCart = new ShoppingCart();
                tempCart.setUid(uid);
                tempCart.setPid(pid);
                tempCart.setQuantity(quantity);
                shoppingCartService.addShoppingCart(tempCart);
            } else {
                shoppingCart.setQuantity(shoppingCart.getQuantity()+quantity);
                shoppingCartService.updateShoppingCart(shoppingCart);
            }
        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }


    @RequestMapping(value = "/getAllShoppingCarts")
    @ResponseBody
    // 1. no login, get list from session
    // 2. already login, get list from database
    public ModelAndView getAllShoppingCarts(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        // exclude error case (currentUser.uid should be set to -1 at header.jsp)
        if (httpSession.getAttribute("currentUser") == null) {return mv;}

        User user = (User) httpSession.getAttribute("currentUser");
        List<ProductInCart> productInCartList = null;
        if (user.getUid() == -1) {
            // anonymous user => get list from session
            productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
        } else {
            // login user => get list from database
            productInCartList = shoppingCartService.listProductInCart(user.getUid());
        }
        mv.addObject("productInCartList", productInCartList);
        mv.setViewName("shoppingCart");
        return mv;
    }


    @RequestMapping(value = "/deleteShoppingCart")
    @ResponseBody
    // 1. no login, delete item from session
    // 2. already login, delete item from database
    public Map<String,Object> deleteShoppingCart(Integer uid,Integer pid, HttpSession httpSession){
        // if anonymous user, delete it from session
        if (uid == -1) {
            List<ProductInCart> productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
            // delete selected productInCart in list
            for (ProductInCart p : productInCartList) {
                if (p.getPid() == pid) {
                    productInCartList.remove(p);
                }
            }
            httpSession.setAttribute("productInCartList", productInCartList);
        } else {
            shoppingCartService.deleteShoppingCart(uid, pid);
        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }


    @RequestMapping(value = "/moveToWishList")
    @ResponseBody
    // if not login, return "please login"
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
    public ModelAndView initializeCart(HttpSession httpSession){
        // clear cart record in session
        httpSession.setAttribute("productInCartList", null);
        return new ModelAndView("redirect:/getAllProducts");
    }

    @RequestMapping(value = "/addOne")
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
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(uid, pid);
            shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);
            shoppingCartService.updateShoppingCart(shoppingCart);
        }
    }

    @RequestMapping(value = "/removeOne")
    @ResponseBody
    public void removeOne(Integer uid, Integer pid, HttpSession httpSession) {
        if (uid == -1) {
            List<ProductInCart> productInCartList = (List<ProductInCart>) httpSession.getAttribute("productInCartList");
            for (ProductInCart p : productInCartList) {
                if (p.getPid() == pid) {
                    if (p.getQuantity() >= 1) {
                        p.setQuantity(p.getQuantity() - 1);
                    }

                }
            }
        } else {
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(uid, pid);
            if (shoppingCart.getQuantity() >= 1) {
                shoppingCart.setQuantity(shoppingCart.getQuantity() - 1);
            }
            shoppingCartService.updateShoppingCart(shoppingCart);
        }
    }

}
