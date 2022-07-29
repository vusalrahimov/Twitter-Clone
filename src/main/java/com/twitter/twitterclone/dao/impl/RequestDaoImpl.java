package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.RequestDao;
import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.Request;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl implements RequestDao {

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public List<Request> getRequestsByReceiverId(Integer receiverId) {
        return null;
    }

    @Override
    public Request getRequestByIds(Integer senderId, Integer receiverId) {
        String sql = "SELECT * FROM REQUEST WHERE SENDER_ID = ? AND RECEIVER_ID = ? AND ACTIVE_STATUS = ?";
        Request request = null;
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                request = new Request();
                request.setId(rs.getInt("ID"));
                request.setSender(userDao.getUserById(senderId));
                request.setReceiver(userDao.getUserById(receiverId));
                request.setDataDate(rs.getDate("DATA_DATE"));
                request.setActiveStatus(EnumStatus.ACTIVE.value);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return request;
    }

    @Override
    public void save(Integer senderId, Integer receiverId) {
        String sql = "INSERT INTO REQUEST(SENDER_ID, RECEIVER_ID, ACTIVE_STATUS, DATA_DATE)" +
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
    public void delete(Integer senderId, Integer receiverId) {
        String sql = "DELETE FROM REQUEST WHERE SENDER_ID = ? AND RECEIVER_ID = ? AND ACTIVE_STATUS = ?";
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

    @Override
    public List<Request> getRequests(Integer receiverId) {
        String sql = "SELECT * FROM REQUEST WHERE RECEIVER_ID = ? AND ACTIVE_STATUS = ?";
        List<Request> requestList = new ArrayList<>();
        try(Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, receiverId);
            ps.setInt(2, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Request request = new Request();
                request.setId(rs.getInt("ID"));
                request.setDataDate(rs.getDate("DATA_DATE"));
                request.setActiveStatus(EnumStatus.ACTIVE.value);
                request.setReceiver(userDao.getUserById(receiverId));
                request.setSender(userDao.getUserById(rs.getInt("SENDER_ID")));
                requestList.add(request);
            }

        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return requestList;
    }
}
