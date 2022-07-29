package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.FollowerDao;
import com.twitter.twitterclone.dao.impl.FollowerDaoImpl;
import com.twitter.twitterclone.service.FollowerService;

public class FollowerServiceImpl implements FollowerService {

    private final FollowerDao followerDao = new FollowerDaoImpl();

    @Override
    public void accept(Integer senderId, Integer receiverId) {
        followerDao.addFriend(senderId, receiverId);
    }

    @Override
    public void remove(Integer senderId, Integer receiverId) {
        followerDao.delete(senderId, receiverId);
    }
}
