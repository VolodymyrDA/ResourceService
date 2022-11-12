let page = 1;
function showData() {
    $.ajax({
        url: 'analytics/lack/?page=' + page,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let resourcesOnHubs = '';
            $.each(data, function (i, resourceOnHub) {
                resourcesOnHubs += '<div class= "row mb-3">' +
                    '<div class="col-3 themed-grid-col">' + resourceOnHub.resourceId + '</div>' +
                    '<div class="col-6 themed-grid-col">' + resourceOnHub.resourceName + '</div>' +
                    '<div class="col-3 themed-grid-col">' + resourceOnHub.quantity + '</div> ' +
                    '</div>';
            });
            $('#resourcesOnHubs').append(resourcesOnHubs);
            resourcesOnHubs++;
            page++;
        }
    });
}

$(function () {
    showData();
});