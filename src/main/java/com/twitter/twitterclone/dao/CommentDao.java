package com.twitter.twitterclone.dao;

import com.twitter.twitterclone.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getComments();
    List<Comment> getCommentsByPostId(Integer postId);
    Comment getCommentById(Integer id);

    int count(Integer postId);

    void post(String comment, Integer tweetId, Integer userId);
}
