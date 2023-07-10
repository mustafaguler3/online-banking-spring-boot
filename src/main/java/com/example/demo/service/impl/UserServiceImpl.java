package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.domain.security.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountService accountService;
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
    @Transactional
    public User createUser(User user, Set<UserRole> roles) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null){
            LOG.info("user with username {} already exist. Nothing will be done");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            for (UserRole role : roles){
                roleRepository.save(role.getRole());
            }
            user.getUserRoles().addAll(roles);
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userRepository.save(user);
        }
        return localUser;
    }
}














