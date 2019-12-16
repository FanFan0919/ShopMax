package com.marlabs.shopping.dao.Implementation;

import com.marlabs.shopping.dao.Interface.ShoppingCartDao;
import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.ShoppingCart;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public ShoppingCart getShoppingCart(Integer uid, Integer pid) {
        String hql = "from ShoppingCart where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        query.setParameter(1, pid);
        return (ShoppingCart) query.uniqueResult();
    }

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        sessionFactory.getCurrentSession().save(shoppingCart);
    }

    @Override
    public boolean deleteShoppingCart(Integer uid, Integer pid) {
        String hql = "delete ShoppingCart where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        query.setParameter(1, pid);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateShoppingCart(ShoppingCart shoppingCart) {
        String hql = "update ShoppingCart set quantity = ? where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,shoppingCart.getQuantity());
        query.setParameter(1,shoppingCart.getUid());
        query.setParameter(2,shoppingCart.getPid());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingCart> getShoppingCartList(Integer uid) {
        String hql = "from ShoppingCart where uid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,uid);
        return query.list();
    }

    @Override
    public List<ProductInCart> listProductInCart(Integer uid) {
        String hql = "select new com.marlabs.shopping.entity.ProductInCart(p.pid, p.name, p.price, s.quantity, p.imgUrl) from Product p, ShoppingCart s where s.uid = ? and s.pid = p.pid";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,uid);
        return query.list();
    }
}
