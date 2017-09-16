function addChannel(element) {
    var nameField = $('#newChannelName');
    $.ajax({
        url: "/newChannel",
        type: "POST",
        contentType: "text/plain",
        data: nameField.val()
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
    nameField.val("");
}