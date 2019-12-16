package com.marlabs.shopping.dao.Implementation;

import com.marlabs.shopping.dao.Interface.WishListDao;
import com.marlabs.shopping.entity.ProductInCart;
import com.marlabs.shopping.entity.WishList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class WishListDaoImpl implements WishListDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public WishList getWishList(Integer uid, Integer pid) {
        String hql = "from WishList where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        query.setParameter(1, pid);
        return (WishList) query.uniqueResult();
    }

    @Override
    public void addWishList(WishList wishList) {
        sessionFactory.getCurrentSession().save(wishList);
    }

    @Override
    public boolean deleteWishList(Integer uid, Integer pid) {
        String hql = "delete WishList where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        query.setParameter(1, pid);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateWishList(WishList wishList) {
        String hql = "update WishList set quantity = ? where uid = ? and pid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,wishList.getQuantity());
        query.setParameter(1,wishList.getUid());
        query.setParameter(2,wishList.getPid());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<WishList> getWishListList(Integer uid) {
        String hql = "from WishList where uid = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,uid);
        return query.list();
    }

    @Override
    public List<ProductInCart> listProductInWishList(Integer uid) {
        String hql = "select new com.marlabs.shopping.entity.ProductInCart(p.pid, p.name, p.price, w.quantity, p.imgUrl) from Product p, WishList w where w.uid = ? and w.pid = p.pid";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,uid);
        return query.list();
    }
}
