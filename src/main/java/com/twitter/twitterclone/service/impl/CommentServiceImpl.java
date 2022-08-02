package com.twitter.twitterclone.service.impl;

import com.twitter.twitterclone.dao.CommentDao;
import com.twitter.twitterclone.dao.impl.CommentDaoImpl;
import com.twitter.twitterclone.db.DatabaseManager;
import com.twitter.twitterclone.enums.EnumStatus;
import com.twitter.twitterclone.service.CommentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao = new CommentDaoImpl();


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
}
