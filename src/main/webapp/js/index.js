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


