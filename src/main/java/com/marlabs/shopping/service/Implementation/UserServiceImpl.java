package com.marlabs.shopping.service.Implementation;

import com.marlabs.shopping.dao.Interface.UserDao;
import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Integer uid) {
        return userDao.getUser(uid);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
