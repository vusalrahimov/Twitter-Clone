<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            <li class="nav-item active p-2" id="requests">
                <a class="nav-link" href="#">Requests</a>
            </li>
            <div class="user-request-container" id="user-request-container">
                <div class="request-container w-100">
                    <div class="user-request w-100 m-2">
                        <span><span>@</span>vrehimov</span>
                            <button class="btn btn-primary">Accept</button>
                        <button class="btn btn-danger">Decline</button>
                    </div>
                    <div class="user-request w-100 m-2">
                        <span><span>@</span>vrehimov</span>
                        <button class="btn btn-primary">Accept</button>
                        <button class="btn btn-danger">Decline</button>
                    </div>
                    <div class="user-request w-100 m-2">
                        <span><span>@</span>vrehimov</span>
                        <button class="btn btn-primary">Accept</button>
                        <button class="btn btn-danger">Decline</button>
                    </div>
                </div>
            </div>
            <li class="nav-item active p-2">
                <a class="nav-link" href="/auth?action=logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="tweet-container w-100 p-5">

    <div class="card m-3">
        <div class="card-body">
            <h5 class="card-title text-primary"><span>@</span>vrehimov</h5>
            <p class="card-text lead">With supporting text below as a natural lead-in to additional content.</p>
            <button class="btn-load-comments" value="close">
                <i class="fa-solid fa-comment"></i>
                <span><span>12</span>&nbsp;comments</span>
            </button>
        </div>

        <div class="write-comment-section m-2 p-2" style="display: none">
            <hr>
            <textarea class="form-control m-2" rows="3" placeholder="Share your comment..."></textarea>
            <button type="button" style="float: right" class="btn btn-primary m-2">Comment</button>
        </div>
        <!--        <div class="read-comment-section m-1 p-2">-->
        <!--            <div class="comment-container m-3">-->
        <!--                <span class="comment-user"><span>@</span>vrehimov:</span>&nbsp;-->
        <!--                <p class="comment-text">Some comments in here Some comments in here Some Some comments in here Some-->
        <!--                    comments in here Some comments iSome comments in here Some comments in here Some comments iSome-->
        <!--                    comments in here Some comments in here Some comments icomments in here Some comments in here Some-->
        <!--                    comments in here Some comments in here </p>-->
        <!--            </div>-->
        <!--        </div>-->
    </div>

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
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Share</button>
            </div>
        </div>
    </div>
</div>

<script src="../../js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function getUsersByUsername(){
        $.ajax({
            url:"/user?username="+$('#search-user').val(),
            type: "GET",
            dataType:"html",
            success:function (html) {
                setUserData(html);
            }
        });
    }

    function setUserData(html){
        $('#search-user').parent().parent().parent().children('.user-container').remove();
        $('#search-user').parent().parent().parent().append(html);
    }
</script>
</body>
</html>