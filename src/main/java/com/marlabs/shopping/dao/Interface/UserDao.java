package com.marlabs.shopping.dao.Interface;

import com.marlabs.shopping.entity.User;
import com.marlabs.shopping.utils.Response;

import java.util.List;

public interface UserDao {
    User getUser(Integer uid);

    User getUser(String username);

    void addUser(User user);

    List<User> getAllUser();
}
