package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.CommentDao;
import com.twitter.twitterclone.dao.PostDao;
import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private UserDao userDao = new UserDaoImpl();
    private PostDao postDao = new PostDaoImpl();

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        String sql = "SELECT * FROM POST_COMMENT WHERE POST_ID = ? AND ACTIVE_STATUS = ? ORDER BY DATA_DATE ASC";
        List<Comment> commentList = new ArrayList<>();
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, postId);
            ps.setInt(2, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt("ID"));
                comment.setPostComment(rs.getString("POST_COMMENT"));
                comment.setUser(userDao.getUserById(rs.getInt("USER_ID")));
                comment.setPost(postDao.getPostById(rs.getInt("POST_ID")));
                commentList.add(comment);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return commentList;
    }

    @Override
    public Comment getCommentById(Integer id) {
        return null;
    }

    @Override
    public int count(Integer postId) {
        String sql = "SELECT COUNT(*) AS COUNT FROM POST_COMMENT WHERE POST_ID = ? AND ACTIVE_STATUS = ?";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, postId);
            ps.setInt(2, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("COUNT");
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return 0;
    }

    @Override
    public void post(String comment, Integer tweetId, Integer userId) {
        String sql = "INSERT INTO POST_COMMENT(POST_COMMENT, POST_ID, USER_ID, DATA_DATE, ACTIVE_STATUS) " +
                "VALUES(?,?,?,?,?)";
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, comment);
            ps.setInt(2, tweetId);
            ps.setInt(3, userId);
            ps.setDate(4, new Date(new java.util.Date().getTime()));
            ps.setInt(5, EnumStatus.ACTIVE.value);
            ps.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
