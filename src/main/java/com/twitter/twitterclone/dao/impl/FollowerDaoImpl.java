package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.FollowerDao;
import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Followings;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FollowerDaoImpl implements FollowerDao {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void addFriend(Integer senderId, Integer receiverId) {
        String sql = "INSERT INTO FOLLOWER(SENDER_ID,RECEIVER_ID,ACTIVE_STATUS,DATA_DATE)" +
                " VALUES(?,?,?,?)";
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ps.setDate(4, new Date(new java.util.Date().getTime()));
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Followings getFriendByIds(Integer senderId, Integer receiverId) {
        String sql = "SELECT * FROM FOLLOWER WHERE SENDER_ID = ? AND RECEIVER_ID = ? AND ACTIVE_STATUS = ?";
        Followings follower = null;
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                follower = new Followings();
                follower.setId(rs.getInt("ID"));
                follower.setSender(userDao.getUserById(senderId));
                follower.setReceiver(userDao.getUserById(receiverId));
                follower.setDataDate(rs.getDate("DATA_DATE"));
                follower.setActiveStatus(EnumStatus.ACTIVE.value);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return follower;
    }

    @Override
    public void delete(Integer senderId, Integer receiverId) {
        String sql = "DELETE FROM FOLLOWER WHERE RECEIVER_ID = ? AND SENDER_ID = ? AND ACTIVE_STATUS = ?";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, receiverId);
            ps.setInt(2, senderId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
