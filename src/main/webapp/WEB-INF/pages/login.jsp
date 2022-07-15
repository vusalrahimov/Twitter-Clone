<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Page</title>
    <link href="../../css/login.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="login-container">

    <form class="login-form">
        <div class="form-group m-4">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Username">
        </div>
        <div class="form-group m-4">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password">
        </div>
        <div class="form-group m-lg-4">
            <span id="error" class="text-danger form-group">Username or password is invalid</span>
        </div>
        <button type="button" class="btn btn-primary m-4">Login</button>
        <div class="form-group m-3">
            <label>Don't you have an account?&nbsp;<a href="/auth?action=register">Sign up</a></label>
        </div>
    </form>

</div>

</body>
</html>