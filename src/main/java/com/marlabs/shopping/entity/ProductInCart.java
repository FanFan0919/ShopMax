package com.marlabs.shopping.entity;

// This is a POJO class to display products in ShoppingCart
public class ProductInCart {
    private Integer pid;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ProductInCart(Integer pid, String name, Double price, Integer quantity, String imgUrl) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }
}
