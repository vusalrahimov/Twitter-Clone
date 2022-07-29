package com.twitter.twitterclone.service;

public interface FollowerService {
    void accept(Integer senderId, Integer receiverId);

    void remove(Integer senderId, Integer receiverId);
}
