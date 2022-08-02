package com.twitter.twitterclone.controller;

import com.twitter.twitterclone.model.Post;
import com.twitter.twitterclone.model.User;
import com.twitter.twitterclone.service.CommentService;
import com.twitter.twitterclone.service.PostService;
import com.twitter.twitterclone.service.impl.CommentServiceImpl;
import com.twitter.twitterclone.service.impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tweet = req.getParameter("tweet");
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        PostService postService = new PostServiceImpl();
        postService.sharePost(tweet, user.getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession(false);
       User user = (User) session.getAttribute("user");
       PostService postService = new PostServiceImpl();
       List<Post> postList = postService.getPosts(user.getId());
       req.setAttribute("postList", postList);
       resp.setContentType("text/html");
       req.getRequestDispatcher("/WEB-INF/data/postData.jsp").forward(req, resp);
    }
}
