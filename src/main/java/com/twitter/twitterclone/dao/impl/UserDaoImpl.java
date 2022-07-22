package com.twitter.twitterclone.dao.impl;

import com.twitter.twitterclone.dao.UserDao;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM USERS WHERE LOWER(USERNAME) = LOWER(?) AND ACTIVE_STATUS = ?";
       try(Connection connection = DatabaseManager.getConnection();
           PreparedStatement ps = connection.prepareStatement(sql)){
           ps.setString(1, username);
           ps.setInt(2, EnumStatus.ACTIVE.value);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               user = new User();
               user.setId(rs.getInt("ID"));
               user.setName(rs.getString("FIRST_NAME"));
               user.setSurname(rs.getString("LAST_NAME"));
               user.setUsername(username);
               user.setPassword(rs.getString("PWD"));
               user.setDataDate(rs.getDate("DATA_DATE"));
               user.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PWD, DATA_DATE, ACTIVE_STATUS)" +
                " VALUES(?,?,?,?,?,?)";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setDate(5, new Date(new java.util.Date().getTime()));
            ps.setInt(6, EnumStatus.ACTIVE.value);
            ps.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM USERS WHERE LOWER(USERNAME) = LOWER(?) AND PWD = ?";
        User user = null;
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("FIRST_NAME"));
                user.setSurname(rs.getString("LAST_NAME"));
                user.setUsername(username);
                user.setPassword(password);
                user.setDataDate(rs.getDate("DATA_DATE"));
                user.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        String sql = "SELECT DISTINCT * FROM USERS WHERE LOWER(USERNAME) LIKE LOWER(?) AND ACTIVE_STATUS = ?";
        List<User> userList = new ArrayList<>();
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, "%"+username+"%");
            ps.setInt(2, EnumStatus.ACTIVE.value);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("FIRST_NAME"));
                user.setSurname(rs.getString("LAST_NAME"));
                user.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
                user.setDataDate(rs.getDate("DATA_DATE"));
                user.setUsername(rs.getString("USERNAME"));
                userList.add(user);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return userList;
    }
}
