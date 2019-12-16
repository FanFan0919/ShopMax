package com.marlabs.shopping.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="WishList")
public class WishList {
    private Integer wid;
    private Integer uid;
    private Integer pid;
    private Integer quantity;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //Auto-Increment
    @GeneratedValue(generator = "generator")

    @Column(name="wid")
    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    @Column(name="uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Column(name="pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name="quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
