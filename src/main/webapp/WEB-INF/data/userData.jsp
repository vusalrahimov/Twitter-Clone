<%@ page import="com.twitter.twitterclone.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.twitter.twitterclone.service.CheckService" %>
<%@ page import="com.twitter.twitterclone.service.impl.CheckServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    User user = (User) request.getSession().getAttribute("user");
    CheckService checkService = new CheckServiceImpl();
%>
<div class="user-container m-2">
    <% for(User u:userList) {
        if (u.getId() == user.getId())
            continue;
        String status = checkService.checkUserStatus(user.getId(), u.getId());
    %>
    <div class="user-profile-container m-2 p-1" >
        <span><span >@</span ><%=u.getUsername()%></span>
        <input type="hidden" class="status-value" value="<%=status%>">
        <button class="btn btn-primary send-request" onclick="sendRequest(this)" value="<%=u.getId()%>"><%=status%></button >
    </div >
    <% } %>
</div>
