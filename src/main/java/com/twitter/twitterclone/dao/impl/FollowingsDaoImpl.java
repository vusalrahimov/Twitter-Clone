package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.FollowingsDao;
import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Followings;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FollowingsDaoImpl implements FollowingsDao {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void addFriend(Integer senderId, Integer receiverId) {
        String sql = "INSERT INTO FOLLOWINGS(SENDER_ID,RECEIVER_ID,ACTIVE_STATUS,DATA_DATE)" +
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
        String sql = "SELECT * FROM FOLLOWINGS WHERE SENDER_ID = ? AND RECEIVER_ID = ? AND ACTIVE_STATUS = ?";
        Followings followings = null;
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                followings = new Followings();
                followings.setId(rs.getInt("ID"));
                followings.setSender(userDao.getUserById(senderId));
                followings.setReceiver(userDao.getUserById(receiverId));
                followings.setDataDate(rs.getDate("DATA_DATE"));
                followings.setActiveStatus(EnumStatus.ACTIVE.value);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return followings;
    }

    @Override
    public void delete(Integer senderId, Integer receiverId) {
        String sql = "DELETE FROM FOLLOWINGS WHERE RECEIVER_ID = ? AND SENDER_ID = ? AND ACTIVE_STATUS = ?";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
