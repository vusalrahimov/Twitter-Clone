package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.FollowingsService;
import com.twitter.twitterclone.service.impl.FollowingsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/followings")
public class FollowingsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("accept")){
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            Integer senderId = user.getId();
            Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
            FollowingsService followingsService = new FollowingsServiceImpl();
            followingsService.accept(senderId, receiverId);

        }else if(action.equalsIgnoreCase("remove")){
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            Integer senderId = user.getId();
            Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
            FollowingsService followingsService = new FollowingsServiceImpl();
            followingsService.remove(senderId, receiverId);
        }
    }
}
