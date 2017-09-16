function addShow(element) {
    $.post('/newShow/' + channelIdToAddShow.toString(), $('#newShow').serialize(), function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}

var channelIdToAddShow = 0;

function newShow(element) {
    channelIdToAddShow = $(element).closest(".channel").find(".hide").html();
    $.post("/genres", function (data) {
        var genresSelect = $('#newShowGenreSelect');
        $.each(data, function (key, genre) {
            var newRow = '<option value="' + genre.name + '">' + genre.name + '</option>';
            genresSelect.append(newRow);
        });
        $('select').material_select();
    });
}

function deleteShow(element) {
    var channelId = $(element).closest(".channel").find(".hide").html();
    var id = $(element).siblings(".hide").html();
    $.ajax({
        url: "/deleteShow",
        type: "POST",
        contentType: "text/plain",
        data: channelId.toString() + " " + id.toString()
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}