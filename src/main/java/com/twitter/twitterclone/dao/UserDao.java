package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();
    User getUserByUsername(String username);
    User getUserById(Integer id);
    void saveUser(User user);

    User getUserByUsernameAndPassword(String username, String password);

    List<User> getUsersByUsername(String username);
}
