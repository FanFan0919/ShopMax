package com.marlabs.shopping.controller;

import com.marlabs.shopping.entity.Product;
import com.marlabs.shopping.service.Interface.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public ModelAndView getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        ModelAndView mv =  new ModelAndView();
        mv.addObject("productList", productList);
        mv.setViewName("main");
        return mv;
    }

    @RequestMapping(value = "/showProductDetails")
    @ResponseBody
    public ModelAndView showProductDetails(Integer pid){
        Product product = productService.getProduct(pid);
        ModelAndView mv =  new ModelAndView();
        mv.addObject("product", product);
        mv.setViewName("productDetail");
        return mv;
    }
}
