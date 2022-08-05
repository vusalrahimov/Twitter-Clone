package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.CommentDao;
import com.twitter.twitterclone.dao.impl.CommentDaoImpl;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Comment;
import com.twitter.twitterclone.service.CommentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao = new CommentDaoImpl();


    @Override
    public int count(Integer postId) {
        return commentDao.count(postId);
    }

    @Override
    public void send(String comment, Integer tweetId, Integer userId) {
        commentDao.post(comment, tweetId, userId);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer tweetId) {
        return commentDao.getCommentsByPostId(tweetId);
    }
}
