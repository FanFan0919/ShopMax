package com.marlabs.shopping.dao.Implementation;

import com.marlabs.shopping.dao.Interface.UserDao;
import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.utils.Response;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public User getUser(Integer uid) {
        String hql = "from User where uid=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        return (User)query.uniqueResult();
    }

    @Override
    public User getUser(String username) {
        String hql = "from User where username=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, username);
        return (User)query.uniqueResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAllUser() {
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
