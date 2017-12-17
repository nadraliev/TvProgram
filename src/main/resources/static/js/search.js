function searchShows() {
    var searchField = $('#searchQuery');
    $.post("/search?query=" + searchField.val(), function (data) {
        $(".channels").html(data);
    });
}