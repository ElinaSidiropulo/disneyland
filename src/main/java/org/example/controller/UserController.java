package org.example.controller;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserDao userDao = new UserDao();

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userDao.saveUser(user);
    }
}
