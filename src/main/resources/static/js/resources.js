let orders = 0;
let page = 1;
let SelectedResourceId = 0;

function showHubResources() {
    $.ajax({
        url: '/hubResources/get?page=' + page,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let orders = '';
            $.each(data, function (i, order) {
                if (order.hubId == 0) {
                    order.hubId = "waiting"
                } else {
                    order.hubId = '<a href="#" class="link-success"><b> confirmed hub ' + order.hubId + '</b></a>'
                }
                ;
                orders += '<div class= "row mb-3">' +
                    '<div class="col-2 themed-grid-col">' + order.resourceId + '</div>' +
                    '<div class="col-8 themed-grid-col">' + order.resourceName + '</div>' +
                    '<div class="col-2 themed-grid-col">' + order.quantity + '</div> ' +
                    '</div>';
            });
            // console.log(data)
            $('#orders').append(orders);
            orders++;
            page++;
        }
    });
}

function showCategories() {
    $.ajax({
        url: 'categories/get',
        dataType: 'json',
        success: function (data) {
            let categories = '';
            $.each(data, function (i, categorie) {
                categories += '<option value="' + categorie.id + '">' + categorie.name + '</option>';
            });
            $('#categories').append(categories);
        }
    });
}

function showSubCategories(category_id) {
    $.ajax({
        url: 'subcategories/get/' + category_id,
        dataType: 'json',
        success: function (data) {
            $('#subcategories').empty();
            let subcategories = '<option>select subcategories</option>';
            $.each(data, function (i, subcategory) {
                subcategories += '<option value="' + subcategory.id + '">' + subcategory.name + '</option>';
            });
            $('#subcategories').append(subcategories);
        }
    });
}

function showResources(subcategory_id) {
    $.ajax({
        url: 'resources/get/' + subcategory_id,
        dataType: 'json',
        success: function (data) {
            $('#resources').empty();
            let resources = '<option>select resource</option>';
            $.each(data, function (i, resource) {
                resources += '<option value="' + resource.id + '">' + resource.name + '</option>';
            });
            $('#resources').append(resources);
        }
    });
}

function submitSupplement(){
    $.ajax({
        url: '/hubResources/add?orders=' + orders,
        type: 'POST',
        data: 'resourceId=' + SelectedResourceId + '&quantity=' + $('#quantity').val(),
        success: function () {
        }
    });
    $('#orders').empty();
    page = 1;
    showHubResources();
}
$(document).on('change', '#categories', function () {
    var category_id = $(this).val();
    $('#subcategories').prop('disabled', false);
    showSubCategories(category_id);
})
$(document).on('change', '#subcategories', function () {
    var subcategory_id = $(this).val();
    $('#resources').prop('disabled', false);
    showResources(subcategory_id);
})
$(document).on('change', '#resources', function () {
    SelectedResourceId = $(this).val();
})
$(function () {
    $('#subcategories').prop('disabled', true);
    $('#resources').prop('disabled', true);
    showCategories();
    showHubResources();
});