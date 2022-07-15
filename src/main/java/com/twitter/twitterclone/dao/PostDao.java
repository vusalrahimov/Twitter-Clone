package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getPosts();
    List<Post> getPostsByUserId(Integer userId);
    Post getPostById(Integer id);

}
