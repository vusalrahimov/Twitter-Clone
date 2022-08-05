
function loadComment(buton) {
    let event = $(buton);
    if (event.val() == "open") {
        event.parent().parent().children('.write-comment-section').hide()
        event.parent().parent().children('.read-comment-section').remove();
        event.val('close');
    } else {
        event.parent().parent().children('.write-comment-section').show();
        event.val('open');
        let tweetId = event.children('input').val();
        getCommentsByTweetId(tweetId, event);
    }
}


function getCommentsByTweetId(tweetId,buton){
    $.ajax({
        url:"/comment?action=getComments",
        type:"GET",
        dataType:"html",
        data:{tweetId:tweetId},
        success:function (html) {
            buton.parent().parent().children('.read-comment-section').remove();
            buton.parent().parent().append(html);
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function sendComment(buton){
    let event = $(buton);
    let tweetId = event.val();
    let comment = event.parent().children('textarea').val();
    $.ajax({
        url:"/comment?action=send",
        type:"POST",
        data:{tweetId:tweetId,comment:comment},
        success:function (){
            getCommentsByTweetId(tweetId, event);
        },
        error: function (err) {
            console.log(err);
        }
    })
}
