function addShow(element) {
    $.post('/newShow', $('#newShow').serialize(), function () {
        $.get("/channel", function (data) {
            tvChannelToRefresh.replaceWith(data);
        });
    });
}

var tvChannelToRefresh;

function newShow(element) {
    tvChannelToRefresh = $(element).parent().parent().parent();
    $.get("/genres", function (data) {
        var genresSelect = $('#newShowGenreSelect');
        $.each(data, function (key, genre) {
            var newRow = '<option value="' + genre.name + '">' + genre.name + '</option>';
            genresSelect.append(newRow);
        });
        $('select').material_select();
    });
}

function deleteShow(element) {
    tvChannelToRefresh = $(element).closest(".channel");
    var tvId = $(element).closest(".row").siblings(".hide").html();
    var id = $(element).siblings(".hide").html();
    $.ajax({
        url: "/deleteShow",
        type: "POST",
        contentType: "text/plain",
        data: tvId.toString() + " " + id.toString()
    }).done(function () {
        $.get("/channel", function (data) {
            tvChannelToRefresh.replaceWith(data);
        });
    });
}