
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

