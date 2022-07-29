$('.btn-load-comments').click(function () {
    let event = $(this);
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
});

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
            console.log("Success");
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





