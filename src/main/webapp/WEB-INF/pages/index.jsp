<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/user.js"></script>
    <script type="text/javascript" src="../../js/comment.js"></script>
    <script type="text/javascript" src="../../js/post.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active p-2">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item active p-2">
                <a class="nav-link " data-toggle="modal" data-target="#search-modal" style="cursor: pointer">Search</a>
            </li>
            <li class="nav-item active p-2">
                <a class="nav-link" data-toggle="modal" data-target="#share-tweet-modal" style="cursor: pointer">Share tweet</a>
            </li>
            <li class="nav-item active p-2" onmouseover="getUserRequests()" id="requests">
                <a class="nav-link" >Requests</a>
            </li>
            <div class="user-request-container" id="user-request-container">
            </div>
            <li class="nav-item active p-2">
                <a class="nav-link" href="/auth?action=logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="tweet-container w-100 p-5" id="tweet-data-container">

</div>


<!--Modal for searching users-->
<!-- Button trigger modal -->
<!-- Modal -->
<div class="modal fade" id="search-modal" tabindex="-1" role="dialog" aria-labelledby="search-modal-label"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="search-modal-label">Search users</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="search-user-data-container" class="modal-body">
                <form>
                    <input type="search" class="form-control" onkeyup="getUsersByUsername()" id="search-user" placeholder="Search user">
                </form>
                <hr>
            </div>

        </div>
    </div>
</div>



<!--Modal for share tweet-->
<!-- Modal -->
<div class="modal fade" id="share-tweet-modal" tabindex="-1" aria-labelledby="share-tweet-modalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="share-tweet-modalLabel">Share tweet</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <textarea id="tweet-input" rows="10" class="form-control" placeholder="Share your idea..."></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="share-section-cls-btn" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="shareTweet()">Share</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>