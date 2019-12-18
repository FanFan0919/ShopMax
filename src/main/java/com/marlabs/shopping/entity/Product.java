package com.marlabs.shopping.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product{
    private Integer pid;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer type;
    private String imgUrl;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //Auto-Increment
    @GeneratedValue(generator = "generator")

    @Column(name="pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name="stock")
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Column(name="type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name="imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
