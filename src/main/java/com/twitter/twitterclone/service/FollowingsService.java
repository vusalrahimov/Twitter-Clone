package com.twitter.twitterclone.service;

public interface FollowingsService {
    void accept(Integer senderId, Integer receiverId);

    void remove(Integer senderId, Integer receiverId);
}
