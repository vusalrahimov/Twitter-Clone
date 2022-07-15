package com.twitter.twitterclone.dao;

public interface FriendsDao {

    void addFriend(Integer followerId, Integer followingsId);

}
