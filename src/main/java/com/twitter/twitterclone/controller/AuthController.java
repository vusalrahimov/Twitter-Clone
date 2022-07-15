package com.twitter.twitterclone.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!=null){
            if(action.equalsIgnoreCase("login")){
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
            }else if(action.equalsIgnoreCase("register")){
                req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
