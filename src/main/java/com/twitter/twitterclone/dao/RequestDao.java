package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.Request;

import java.util.List;

public interface RequestDao {
    List<Request> getRequestsByReceiverId(Integer receiverId);
}
