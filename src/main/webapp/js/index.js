function loadComment(buton) {
    let event = $(buton);
    if (event.val() == "open") {
        event.parent().parent().children('.write-comment-section').hide()
        event.parent().parent().children('.read-comment-section').remove();
        event.val('close');
    } else {
        let html = "        <div class=\"read-comment-section m-1 p-2\">\n" +
            "            <div class=\"comment-container m-3\">\n" +
            "                <span class=\"comment-user\"><span>@</span>vrehimov:</span>&nbsp;\n" +
            "                <p class=\"comment-text\">Some comments in here Some comments in here Some Some comments in here Some\n" +
            "                    comments in here Some comments iSome comments in here Some comments in here Some comments iSome\n" +
            "                    comments in here Some comments in here Some comments icomments in here Some comments in here Some\n" +
            "                    comments in here Some comments in here </p>\n" +
            "            </div>\n" +
            "        </div>";
        event.parent().parent().append(html + html);
        event.parent().parent().children('.write-comment-section').show();
        event.val('open');
    }
}


function getUsersByUsername(){
    callUsers();
}

function callUsers(){
    setInterval(function (){
        $.ajax({
            url: "/user?username=" + $('#search-user').val(),
            type: "GET",
            dataType: "html",
            success: function (html) {

                setUserData(html);
            },
            error:function (err) {
                console.log(err);
            }
        });
    }, 4000);
}

function setUserData(html){
    $('#search-user').parent().parent().parent().children('.user-container').remove();
    $('#search-user').parent().parent().parent().append(html);
}



function sendRequest(event){
    let buton = $(event);
    let status = buton.parent().children('.status-value').val();

    let url = "";
    if (status == "follow") {
        url = "/request?action=send";
    } else if (status == "requested") {
        url = "/request?action=remove";
    } else if (status == "following") {
        url = "/follower?action=unfollow";
    }
    let data = {receiverId: buton.val()}
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function () {
            if (status == "follow") {
                buton.parent().children('.status-value').val('requested');
                buton.html('requested');
            } else if (status == "requested") {
                buton.parent().children('.status-value').val('follow');
                buton.html('follow');
            } else if (status == "following") {
                buton.parent().children('.status-value').val('follow');
                buton.html('follow');
            }
        }
    });
}

function getUserRequests(){
    $('#user-request-container').children('#request-container').remove();
    $.ajax({
        url:"/request?action=getRequests",
        type:"GET",
        async: false,
        dataType: "html",
        success: function (html) {
           $('#user-request-container').append(html);
        },
        error:function (err) {
            console.log(err)
        }
    });
}

function confirmRequest(event){
    let buton = $(event);
    let data = {senderId:buton.val()}
    $.ajax({
        url:"/request?action=confirm",
        type:"POST",
        data: data,
        success:function () {
            buton.parent().remove();
        }
    });
}

function deleteRequest(event){
    let buton = $(event);
    let data = {senderId:buton.val()}
    $.ajax({
        url:"/request?action=delete",
        type:"POST",
        data: data,
        success:function () {
            buton.parent().remove();
        }
    });
}

function getPosts(){
    $.ajax({
        url:"/post",
        type:"GET",
        dataType:"html",
        success: function (html) {
            $('#tweet-data-container').html(html);
        },
        error:function (err) {
            console.log(err);
        }
    })
}

$(window).on( "load", function() {
    getPosts();
});


function shareTweet(){
    $.ajax({
        url:"/post",
        type:"POST",
        data:{tweet:$('#tweet-input').val()},
        success:function (){
            $('#tweet-input').val('');
            getPosts();
            $('#share-section-cls-btn').click();
        },
        error:function (err) {
            console.log(err);
        }
    })
}





