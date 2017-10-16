package com.gmail.psse69.service;

import com.gmail.psse69.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
