package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.FollowingDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class FollowingDaoImpl implements FollowingDao {



    @Override
    public void add(Integer senderId, Integer receiverId) {
        String sql = "INSERT INTO FOLLOWING(SENDER_ID,RECEIVER_ID,ACTIVE_STATUS,DATA_DATE)" +
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
}
