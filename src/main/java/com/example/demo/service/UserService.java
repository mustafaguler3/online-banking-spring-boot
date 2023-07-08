package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

import java.util.Set;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExists(String username,String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    void save(User user);
    User createUser(User user, Set<Role> roles);
}
