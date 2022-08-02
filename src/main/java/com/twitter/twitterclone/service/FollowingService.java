package com.twitter.twitterclone.service;

public interface FollowingService {
    void add(Integer senderId,Integer receiverId);

    void remove(Integer senderId, Integer receiverId);
}
