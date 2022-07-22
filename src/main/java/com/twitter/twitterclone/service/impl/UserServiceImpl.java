package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.dao.impl.UserDaoImpl;
import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        if(user.getName() == null || user.getSurname() == null || user.getUsername() == null || user.getPassword() == null)
            return false;
        if(userDao.getUserByUsername(user.getUsername()) != null)
            return false;
        userDao.saveUser(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || password == null)
            return null;
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return userDao.getUsersByUsername(username);
    }
}
