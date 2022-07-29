package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.FollowingDao;
import com.twitter.twitterclone.dao.impl.FollowingDaoImpl;
import com.twitter.twitterclone.service.FollowingService;

public class FollowingServiceImpl implements FollowingService {

    private final FollowingDao followingDao = new FollowingDaoImpl();

    @Override
    public void add(Integer senderId, Integer receiverId) {
        followingDao.add(senderId, receiverId);
    }
}
