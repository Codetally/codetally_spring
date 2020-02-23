package com.codetally.service;


import com.codetally.model.User;
import com.codetally.repository.RoleRepository;
import com.codetally.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User encryptuser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getOne(Long userid) {
        return userRepository.getOne(userid);
    }

    @Override
    public List<User> findUsers(User probe) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("username", contains());

        Example<User> searchresults = Example.of(probe, matcher);
        return userRepository.findAll(searchresults);
    }

}
