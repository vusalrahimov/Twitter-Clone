package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.FollowerService;
import com.twitter.twitterclone.service.impl.FollowerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/follower")
public class FollowerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action.equalsIgnoreCase("unfollow")){
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            Integer senderId = user.getId();
            Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
            FollowerService followerService = new FollowerServiceImpl();
            followerService.remove(senderId, receiverId);
        }
    }

}
