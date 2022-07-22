package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.UserService;
import com.twitter.twitterclone.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username==null)
            return;
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getUsersByUsername(username);
        req.setAttribute("userList", userList);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/data/userData.jsp").forward(req, resp);

    }
}
