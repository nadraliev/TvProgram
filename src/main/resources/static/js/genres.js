function addGenre(element) {
    var nameField = $('#newGenreName');
    $.ajax({
        url: "/genres",
        type: "PUT",
        contentType: "text/plain",
        data: nameField.val()
    }).done(function () {
        $.get("/genres", function (genresRendered) {
            $("#genresListContainer").html(genresRendered);
        });
    });
    nameField.val("");
}

function deleteGenre(element) {
    var li = $(element).closest("li");
    var index = $(li.parent().children()).index(li) - 1;
    $.ajax({
        url: "/genres/" + index,
        type: "DELETE"
    }).done(function () {
        $.get("/genres", function (genresRendered) {
            $("#genresListContainer").html(genresRendered);
        });
    });
}