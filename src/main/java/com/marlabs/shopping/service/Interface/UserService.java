package com.marlabs.shopping.service.Interface;

import com.marlabs.shopping.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Integer uid);

    User getUser(String username);

    void addUser(User user);

    List<User> getAllUser();
}
