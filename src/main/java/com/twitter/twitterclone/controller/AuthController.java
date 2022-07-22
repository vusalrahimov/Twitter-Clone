package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.UserService;
import com.twitter.twitterclone.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "auth",value = "/auth")
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!=null){
            if(action.equalsIgnoreCase("login")){
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
            }else if(action.equalsIgnoreCase("register")){
                req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req,resp);
            }else if (action.equalsIgnoreCase("logout")) {
                  HttpSession session = req.getSession();
                  session.removeAttribute("user");
                  resp.sendRedirect("/auth?action=login");
            } else{
                req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
            }
        }else{
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!=null){


            if(action.equalsIgnoreCase("login")){
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                UserService userService = new UserServiceImpl();
                User user = userService.login(username,password);
                if(user == null){
                  String error = "Username or password is invalid";
                  req.setAttribute("error", error);
                  req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
                }else{
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);
                    resp.sendRedirect("/home");
                }
            }

            else if(action.equalsIgnoreCase("register")){
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                UserService userService = new UserServiceImpl();
                boolean success = userService.register(new User(null, name, surname, username, password));
                if(success){
                    resp.sendRedirect("/auth?action=login");
                }else{
                    String error = "Data is not valid";
                    req.setAttribute("error",error);
                    req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req,resp);
                }
            }else{
                req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
            }
        }else{
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }
    }
}
