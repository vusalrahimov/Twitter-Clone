package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.RequestService;
import com.twitter.twitterclone.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

       }else if(action.equalsIgnoreCase("remove")) {
           HttpSession session = req.getSession(false);
           User user = (User) session.getAttribute("user");
           Integer senderId = user.getId();
           Integer receiverId = Integer.valueOf(req.getParameter("receiverId"));
           RequestService requestService = new RequestServiceImpl();
           requestService.remove(senderId,receiverId);
       }
    }
}
