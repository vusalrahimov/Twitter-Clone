<%@ page import="com.twitter.twitterclone.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.twitter.twitterclone.service.CommentService" %>
<%@ page import="com.twitter.twitterclone.service.impl.CommentServiceImpl" %>
<%
    List<Post> postList = (List<Post>) request.getAttribute("postList");
    CommentService commentService = new CommentServiceImpl();
%>
<%for(Post p: postList){%>
<div class="card m-3">
    <div class="card-body">
        <h5 class="card-title text-primary"><span>@</span><%=p.getUser().getUsername()%></h5>
        <p class="card-text lead"><%=p.getTweet()%></p>
        <button class="btn-load-comments" onclick="loadComment(this)" value="close">
            <input type="hidden" value="<%=p.getId()%>">
            <i class="fa-solid fa-comment"></i>
            <span><span><%=commentService.count(p.getId())%></span>&nbsp;comments</span>
        </button>
    </div>

    <div class="write-comment-section m-2 p-2" style="display: none">
        <hr>
        <textarea class="form-control m-2" rows="3" placeholder="Share your comment..."></textarea>
        <button type="button" style="float: right" value="<%=p.getId()%>" onclick="sendComment(this)" class="btn btn-primary m-2">Comment</button>
    </div>


</div>
<%}%>