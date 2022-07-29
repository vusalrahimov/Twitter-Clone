package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.Request;

import java.util.List;

public interface RequestDao {
    List<Request> getRequestsByReceiverId(Integer receiverId);

    Request getRequestByIds(Integer senderId, Integer receiverId);

    void save(Integer senderId, Integer receiverId);

    void delete(Integer senderId, Integer receiverId);

    List<Request> getRequests(Integer receiverId);
}
