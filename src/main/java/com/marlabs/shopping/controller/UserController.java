package com.marlabs.shopping.controller;

import com.marlabs.shopping.entity.ShoppingCart;
import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.service.Interface.ShoppingCartService;
import com.marlabs.shopping.service.Interface.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String password, String email, String nickname) {
        String result = "fail";
        User user = userService.getUser(userName);
        if (user != null) {
            result = "nameExist";
        } else {
            user = userService.getUser(email);
            if (user != null)
                result = "emailExist";
            else {
                User user1 = new User();
                user1.setUsername(userName);
                user1.setPassword(password);
                user1.setEmail(email);
                user1.setNickname(nickname);
                userService.addUser(user1);
                result = "success";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String username, String password, HttpSession httpSession) {

        Object temp = httpSession.getAttribute("currentUser");
        Integer oldUid = ((User)(temp)).getUid();

        User user = userService.getUser(username);

        String result = "fail";

        if (user == null)
            result = "wrongUser";
        else {
            if (user.getPassword().equals(password)) {
                result = "success";
                httpSession.setAttribute("currentUser", user);
            } else
                result = "wrongPassword";
        }
        //if there is already a anonymous user, merge his shoppingCart to yours
        Integer newUid = user.getUid();
        if (oldUid == -1) {
            // There is already a existing user. check his shopping cart
            List<ShoppingCart> list = shoppingCartService.getShoppingCartList(-1);
            if (list.size() > 0) {
                for (ShoppingCart oldCart : list) {
                    // Add record to new user
                    if (shoppingCartService.getShoppingCart(newUid, oldCart.getPid()) != null) {
                        // This record already exists, merge it
                        ShoppingCart existingShoppingCart = shoppingCartService.getShoppingCart(newUid, oldCart.getPid());
                        existingShoppingCart.setQuantity(existingShoppingCart.getQuantity() + oldCart.getQuantity());
                        shoppingCartService.updateShoppingCart(existingShoppingCart);
                    } else {
                        // This record doesn't exist, create a new one
                        ShoppingCart newCart = new ShoppingCart();
                        newCart.setUid(newUid);
                        newCart.setPid(oldCart.getPid());
                        newCart.setQuantity(oldCart.getQuantity());
                        shoppingCartService.addShoppingCart(newCart);
                    }
                    // Delete record from old user
                    shoppingCartService.deleteShoppingCart(oldCart.getUid(), oldCart.getPid());
                }
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        User user = new User();
        user.setUid(-1);
        httpSession.setAttribute("currentUser",user);
        return "redirect:login";
    }
}
