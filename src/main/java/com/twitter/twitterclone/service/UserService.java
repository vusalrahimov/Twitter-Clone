package com.twitter.twitterclone.service;

import com.twitter.twitterclone.model.User;

public interface UserService {
    boolean register(User user);

    User login(String username, String password);
}
