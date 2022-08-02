package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.PostDao;
import com.twitter.twitterclone.dao.impl.PostDaoImpl;
import com.twitter.twitterclone.model.Post;
import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    public void sharePost(String tweet, Integer userId) {
        postDao.save(tweet, userId);
    }

    @Override
    public List<Post> getPosts(Integer id) {
        return postDao.getPostsByFollowerId(id);
    }
}
