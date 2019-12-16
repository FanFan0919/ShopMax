package com.marlabs.shopping.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {
    private Integer sid;
    private Integer uid;
    private Integer pid;
    private Integer quantity;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //Auto-Increment
    @GeneratedValue(generator = "generator")

    @Column(name="sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", quantity=" + quantity +
                '}';
    }
}
