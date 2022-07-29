<%@ page import="com.twitter.twitterclone.model.Request" %>
<%@ page import="java.util.List" %>
<%
    List<Request> requestList = (List<Request>) request.getAttribute("requestList");
%>
<div class="request-container w-100" id="request-container">
    <%for(Request r: requestList){%>
    <div class="user-request w-100 m-2">
        <span><span>@</span><%=r.getSender().getUsername()%></span>
        <input type="hidden" class="request-id" value="<%=r.getId()%>">
        <button class="btn btn-primary" onclick="confirmRequest(this)" value="<%=r.getSender().getId()%>">Confirm</button>
        <button class="btn btn-danger" onclick="deleteRequest(this)" value="<%=r.getSender().getId()%>">Delete</button>
    </div>
    <%}%>
</div>