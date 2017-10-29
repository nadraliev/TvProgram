function addGenre(element) {
    var nameField = $('#newGenreName');
    $.ajax({
        url: "/newGenre",
        type: "POST",
        contentType: "text/plain",
        data: nameField.val()
    });
    if (nameField.val().trim() != "") {
        var genresLis = $('#genres').find("li");
        var newLine = '<li class="collection-item"><div>' + nameField.val() + '<a onclick="deleteGenre(this)"  href="#!" class="secondary-content"><i class="material-icons md-dark">clear</i></a></div></li>';
        genresLis.last().before(newLine);
    }
    nameField.val("");
}

function deleteGenre(element) {
    var genresList = $('#genres');
    var li = $(element).closest("li");
    var index = $(li.parent().children()).index(li) - 1;
    $.ajax({
        url: "/deleteGenre",
        type: "POST",
        contentType: "text/plain",
        data: index.toString()
    });
    $($(element).parent().parent(), genresList).remove();
}