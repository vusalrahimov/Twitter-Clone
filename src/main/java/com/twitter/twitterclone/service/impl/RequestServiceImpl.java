package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.RequestDao;
import com.twitter.twitterclone.dao.impl.RequestDaoImpl;
import com.twitter.twitterclone.model.Request;
import com.twitter.twitterclone.service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private final RequestDao requestDao = new RequestDaoImpl();

    @Override
    public void send(Integer senderId, Integer receiverId) {
       requestDao.save(senderId, receiverId);
    }

    @Override
    public void remove(Integer senderId, Integer receiverId) {
        requestDao.delete(senderId, receiverId);
    }

    @Override
    public List<Request> getRequests(Integer id) {
        return requestDao.getRequests(id);
    }

    @Override
    public void deleteRequest(Integer senderId, Integer receiverId) {
       requestDao.delete(senderId, receiverId);
    }

}
