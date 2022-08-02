package com.twitter.twitterclone.service;

import com.twitter.twitterclone.model.Post;
import com.twitter.twitterclone.model.User;

import java.util.List;

public interface PostService {
    void sharePost(String tweet, Integer userId);

    List<Post> getPosts(Integer id);
}
