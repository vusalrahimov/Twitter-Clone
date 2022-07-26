package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.FollowingsDao;
import com.twitter.twitterclone.dao.impl.FollowingsDaoImpl;
import com.twitter.twitterclone.service.FollowingsService;

public class FollowingsServiceImpl implements FollowingsService {

    private final FollowingsDao followingsDao = new FollowingsDaoImpl();

    @Override
    public void accept(Integer senderId, Integer receiverId) {
        followingsDao.addFriend(senderId, receiverId);
    }

    @Override
    public void remove(Integer senderId, Integer receiverId) {
        followingsDao.delete(senderId, receiverId);
    }
}
