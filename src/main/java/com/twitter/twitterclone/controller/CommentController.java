package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.Comment;
import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.CommentService;
import com.twitter.twitterclone.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/comment")
public class CommentController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("send")){
            String comment = req.getParameter("comment");
            Integer tweetId = Integer.valueOf(req.getParameter("tweetId"));
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            CommentService commentService = new CommentServiceImpl();
            commentService.send(comment, tweetId, user.getId());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("getComments")){
            Integer tweetId = Integer.valueOf(req.getParameter("tweetId"));
            CommentService commentService = new CommentServiceImpl();
            List<Comment> commentList = commentService.getCommentsByPostId(tweetId);
            req.setAttribute("commentList", commentList);
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/data/commentData.jsp").forward(req, resp);
        }
    }
}
