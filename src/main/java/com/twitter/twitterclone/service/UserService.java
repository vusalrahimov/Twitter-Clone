package com.twitter.twitterclone.service;

import com.twitter.twitterclone.model.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    User login(String username, String password);

    List<User> getUsersByUsername(String username);
}
