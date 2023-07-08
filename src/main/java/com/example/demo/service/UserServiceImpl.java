package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        return false;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (findByUsername(username) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (findByEmail(email) != null){
            return true;
        }
        return false;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User createUser(User user, Set<Role> roles) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null){
            LOG.info("user with username {} already exist. Nothing will be done");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            for (Role role : roles){
                roleRepository.save(role.getRole());
            }
            user.getRoles().addAll(roles);
        }
        return null;
    }
}














