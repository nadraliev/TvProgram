function addChannel(element) {
    var nameField = $('#newChannelName');
    $.ajax({
        url: "/channels",
        type: "PUT",
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
        url: "/channels/" + channelId,
        type: "DELETE"
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}