package com.twitter.twitterclone.service;

import com.twitter.twitterclone.model.Comment;

import java.util.List;

public interface CommentService {

    int count(Integer postId);

    void send(String comment, Integer tweetId, Integer userId);

    List<Comment> getCommentsByPostId(Integer tweetId);
}
