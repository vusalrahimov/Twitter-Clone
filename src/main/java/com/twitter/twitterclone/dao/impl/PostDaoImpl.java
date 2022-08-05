package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.PostDao;
import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Post;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<Post> getPosts() {
        return null;
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return null;
    }

    @Override
    public Post getPostById(Integer id) {
        String sql = "SELECT * FROM POST WHERE ID = ? AND ACTIVE_STATUS = ?";
        Post post = null;
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.setInt(2, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                post = new Post();
                post.setId(rs.getInt("ID"));
                post.setTweet(rs.getString("TWEET"));
                post.setActiveStatus(EnumStatus.ACTIVE.value);
                post.setUser(userDao.getUserById(rs.getInt("USER_ID")));
                post.setDataDate(rs.getDate("DATA_DATE"));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return post;
    }

    @Override
    public void save(String tweet, Integer userId) {
        String sql = "INSERT INTO POST(TWEET,USER_ID,DATA_DATE,ACTIVE_STATUS) VALUES(?,?,?,?)";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, tweet);
            ps.setInt(2, userId);
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            ps.setInt(4, EnumStatus.ACTIVE.value);
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Post> getPostsByFollowerId(Integer id) {
        String sql = "SELECT * FROM POST p WHERE ACTIVE_STATUS = ? AND (p.USER_ID = ANY" +
                " (SELECT RECEIVER_ID FROM FOLLOWING WHERE SENDER_ID = ? AND ACTIVE_STATUS = ?) OR p.USER_ID = ?) ORDER BY p.DATA_DATE ASC";
        List<Post> postList = new ArrayList<>();
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, EnumStatus.ACTIVE.value);
            ps.setInt(2, id);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ps.setInt(4, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setId(rs.getInt("ID"));
                post.setTweet(rs.getString("TWEET"));
                post.setUser(userDao.getUserById(rs.getInt("USER_ID")));
                post.setDataDate(rs.getDate("DATA_DATE"));
                post.setActiveStatus(EnumStatus.ACTIVE.value);
                postList.add(post);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return postList;
    }
}
