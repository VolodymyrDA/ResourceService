let orders = 0;
let page = 1;
let SelectedResourceId = 0;

function showOrders() {
    $.ajax({
        url: 'orders/get?page=' + page,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let orders = '';
            $.each(data, function (i, order) {
                if (order.hubId == 0) {
                    order.hubId = "waiting"
                } else {
                    order.hubId = '<a href="/hub/'+ order.hubId+'" class="link-success"><b> confirmed hub ' + order.hubId + '</b></a>'
                }
                ;
                orders += '<div class= "row mb-3">' +
                    '<div class="col-1 themed-grid-col">' + order.id + '</div>' +
                    '<div class="col-1 themed-grid-col">' + order.resourceId + '</div>' +
                    '<div class="col-5 themed-grid-col">' + order.resourceName + '</div>' +
                    '<div class="col-2 themed-grid-col">' + order.quantity + '</div> ' +
                    '<div class="col-3 themed-grid-col" id=+order"id">' + order.hubId + '</div> ' +
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
        url: 'categories/',
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
        url: 'subcategories/' + category_id,
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
        url: 'resources/' + subcategory_id,
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

function addOrder() {
    $.ajax({
        url: 'orders/add?orders=' + orders,
        type: 'POST',
        data: 'resourceId=' + SelectedResourceId + '&quantity=' + $('#quantity').val(),
        success: function () {
        }
    });
}

function submitOrder(){
    addOrder();
    $('#orders').empty();
    page = 1;
    showOrders();
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
    showOrders();
});