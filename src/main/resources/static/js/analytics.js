let page = 1;

function showData() {
    const analyticsType = $("meta[name='analyticsType']").attr("content");
    $.ajax({
        url: 'analytics/?type=' + analyticsType + '&page=' + page,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let content = '';
            $.each(data, function (i, resourceOnHub) {
                content += '<div class= "row mb-3">' +
                    '<div class="col-3 themed-grid-col">' + resourceOnHub.resourceId + '</div>' +
                    '<div class="col-6 themed-grid-col">' + resourceOnHub.resourceName + '</div>' +
                    '<div class="col-3 themed-grid-col">' + resourceOnHub.quantity + '</div> ' +
                    '</div>';
            });
            $('#content').append(content);
            page++;
        }
    });
}

$(function () {
    showData();
});