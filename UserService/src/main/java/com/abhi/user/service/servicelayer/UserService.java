package com.abhi.user.service.servicelayer;

import com.abhi.user.service.entity.User;

import java.util.List;

public interface UserService {

    // create user
    User saveUser(User user);

    // getting all users
    List<User> getAllUsers();

    // Getting particular user
    User getUser(String userId);
}
