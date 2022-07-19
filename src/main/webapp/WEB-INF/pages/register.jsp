<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Page</title>
    <link href="../../css/register.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="register-container">
    <form class="register-form" method="post" action="/auth?action=register">
        <div class="form-group m-4">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name">
        </div>
        <div class="form-group m-4">
            <label for="surname">Surname</label>
            <input type="text" class="form-control" name="surname" id="surname" placeholder="Surname">
        </div>
        <div class="form-group m-4">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username">
        </div>
        <div class="form-group m-4">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        </div>
        <div class="form-group m-lg-4">
            <%
                String message = (String)request.getAttribute("error");
            %>
            <span id="error" class="text-danger form-group"><%=message==null?"":message%></span>
        </div>
        <button type="submit" class="btn btn-primary m-4">Sign up</button>
        <div class="form-group m-3">
            <label>You have an already an account?&nbsp;<a href="/auth?action=login">Login</a></label>
        </div>
    </form>
</div>

</body>
</html>