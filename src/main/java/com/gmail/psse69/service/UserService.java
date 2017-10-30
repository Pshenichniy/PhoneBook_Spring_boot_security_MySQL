package com.gmail.psse69.service;

import com.gmail.psse69.model.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
