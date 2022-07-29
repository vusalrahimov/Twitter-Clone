package com.twitter.twitterclone.service;

import com.twitter.twitterclone.model.Request;

import java.util.List;

public interface RequestService {
    void send(Integer senderId, Integer receiverId);

    void remove(Integer senderId, Integer receiverId);

    List<Request> getRequests(Integer id);
    void deleteRequest(Integer senderId, Integer id);
}
