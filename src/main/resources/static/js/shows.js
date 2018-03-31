function addShow(element) {
    $.ajax({
        url: '/channels/' + channelIdToAddShow.toString() + '/tvShows',
        type: "PUT",
        contentType: "application/x-www-form-urlencoded",
        data: $('#newShow').serialize()
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}

var channelIdToAddShow = 0;

function newShow(element) {
    channelIdToAddShow = $(element).closest(".channel").find(".hide").html();
    $.get("/genres/names", function (data) {
        var genresSelect = $('#newShowGenreSelect');
        genresSelect.html("");
        $('select').material_select();
        $.each(data, function (key, genreName) {
            var newRow = '<option value="' + genreName + '">' + genreName + '</option>';
            genresSelect.append(newRow);
        });
        $('select').material_select();
    });
}

function deleteShow(element) {
    var channelId = $(element).closest(".channel").find(".hide").html();
    var showId = $(element).siblings(".hide").html();
    $.ajax({
        url: "/channels/" + channelId + "/tvShows/" + showId,
        type: "DELETE"
    }).done(function () {
        $.post("/channels", function (data) {
            $(".channels").html(data);
        });
    });
}