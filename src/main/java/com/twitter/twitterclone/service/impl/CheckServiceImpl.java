package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.FollowerDao;
import com.twitter.twitterclone.dao.RequestDao;
import com.twitter.twitterclone.dao.impl.FollowerDaoImpl;
import com.twitter.twitterclone.dao.impl.RequestDaoImpl;
import com.twitter.twitterclone.service.CheckService;

public class CheckServiceImpl implements CheckService {

    private final RequestDao requestDao = new RequestDaoImpl();
    private final FollowerDao followerDao = new FollowerDaoImpl();

    @Override
    public String checkUserStatus(Integer senderId, Integer receiverId) {
        if(requestDao.getRequestByIds(senderId, receiverId)!=null)
            return "requested";
        else if(followerDao.getFriendByIds(senderId,receiverId)!=null)
            return "following";
        else
            return "follow";
    }
}
