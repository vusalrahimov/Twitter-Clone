package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.RequestDao;
import com.twitter.twitterclone.dao.impl.RequestDaoImpl;
import com.twitter.twitterclone.service.RequestService;

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
}
