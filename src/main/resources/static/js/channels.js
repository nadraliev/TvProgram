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

function deleteChannel(element) {
    var channelId = $(element).closest(".channel").find(".hide").html();
    $.ajax({
        url: "/deleteChannel",
        type: "POST",
        contentType: "text/plain",
        data: channelId.toString()
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}