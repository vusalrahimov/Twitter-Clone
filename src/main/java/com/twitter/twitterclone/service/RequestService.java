package com.twitter.twitterclone.service;

public interface RequestService {
    void send(Integer senderId, Integer receiverId);

    void remove(Integer senderId, Integer receiverId);
}
