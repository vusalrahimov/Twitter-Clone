package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();
    List<User> getUsersByUsername(String username);
    User getUserById(Integer id);

}
