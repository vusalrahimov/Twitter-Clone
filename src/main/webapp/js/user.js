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




