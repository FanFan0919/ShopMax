package com.marlabs.shopping.dao.Implementation;

import com.marlabs.shopping.dao.Interface.ProductDao;
import com.marlabs.shopping.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(Integer pid) {
        String hql = "from Product where pid=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, pid);
        return (Product) query.uniqueResult();
    }

    @Override
    public Product getProduct(String name) {
        String hql = "from Product where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        return (Product) query.uniqueResult();
    }

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public List<Product> getProductsByType(Integer type) {
        String hql = "from Product where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();
    }

    @Override
    public List<Product> getAllProducts() {
        String hql = "from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
