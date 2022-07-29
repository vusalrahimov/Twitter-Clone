package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.Request;
import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.FollowerService;
import com.twitter.twitterclone.service.FollowingService;
import com.twitter.twitterclone.service.RequestService;
import com.twitter.twitterclone.service.impl.FollowerServiceImpl;
import com.twitter.twitterclone.service.impl.FollowingServiceImpl;
import com.twitter.twitterclone.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/request")
public class RequestController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");

       if(action.equalsIgnoreCase("send")){
           HttpSession session = req.getSession(false);
           User user = (User) session.getAttribute("user");
           Integer senderId = user.getId();
           Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
           RequestService requestService = new RequestServiceImpl();
           requestService.send(senderId,receiverId);

       }

       else if(action.equalsIgnoreCase("remove")) {
           HttpSession session = req.getSession(false);
           User user = (User) session.getAttribute("user");
           Integer senderId = user.getId();
           Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
           RequestService requestService = new RequestServiceImpl();
           requestService.remove(senderId,receiverId);
       }

       else if(action.equalsIgnoreCase("delete")){
           HttpSession session = req.getSession(false);
           Integer senderId = Integer.parseInt(req.getParameter("senderId"));
           User user = (User)session.getAttribute("user");
           RequestService requestService = new RequestServiceImpl();
           requestService.deleteRequest(senderId, user.getId());
       }

       else if(action.equalsIgnoreCase("confirm")){
           HttpSession session = req.getSession(false);
           Integer senderId = Integer.parseInt(req.getParameter("senderId"));
           User user = (User)session.getAttribute("user");
           RequestService requestService = new RequestServiceImpl();
           FollowerService followerService = new FollowerServiceImpl();
           FollowingService followingService = new FollowingServiceImpl();
           requestService.deleteRequest(senderId, user.getId());
           followerService.accept(senderId, user.getId());
           followingService.add(senderId, user.getId());
       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("getRequests")) {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            RequestService requestService = new RequestServiceImpl();
            List<Request> requestList = requestService.getRequests(user.getId());
            req.setAttribute("requestList", requestList);
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/data/requestData.jsp").forward(req, resp);
        }
    }
}
