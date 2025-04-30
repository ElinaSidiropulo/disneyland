package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

}

