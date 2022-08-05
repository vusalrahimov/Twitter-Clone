<%@ page import="com.twitter.twitterclone.model.Comment" %>
<%@ page import="java.util.List" %><%
    List<Comment> commentList = (List<Comment>) request.getAttribute("commentList");
%>
<%for(Comment c: commentList){%>
        <div class="read-comment-section m-1 p-2">
            <div class="comment-container m-3">
                <span class="comment-user"><span>@</span><%=c.getUser().getUsername()%>:</span>&nbsp;
                <p class="comment-text"><%=c.getPostComment()%></p>
            </div>
       </div>
<%}%>