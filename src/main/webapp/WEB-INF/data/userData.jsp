<%@ page import="com.twitter.twitterclone.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    User user = (User) request.getSession().getAttribute("user");
%>
<div class="user-container m-2">
    <% for(User u:userList) {
        if (u.getId() == user.getId())
            continue;
    %>
    <div class="user-profile-container m-2 p-1" >
        <span><span >@</span ><%=u.getUsername()%></span >
        <input type="hidden" name="userId" value="<%=u.getId()%>">
        <button class="btn btn-primary" > Follow </button >
    </div >
    <% } %>
</div>
