let page = 1;
let confirmedPage = 1;
function showOrders() {
    $.ajax({
        url: 'hubs/availableOrders?page=' + page,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let orders = '';
            $.each(data, function (i, order) {
                orders += '<div class= "row mb-3">' +
                    '<div class="col-1 themed-grid-col">' + order.orderId +
                    '</div><div class="col-1 themed-grid-col">' + order.locationName +
                    '</div><div class="col-4 themed-grid-col">' + order.userDescription +
                    '</div><div class="col-2 themed-grid-col">' + order.userPhone +
                    '</div><div class="col-2 themed-grid-col">' + order.resourceID +
                    '</div><div class="col-1 themed-grid-col">' + order.orderResourceQuantity +
                    '</div><div class="col-1 themed-grid-col">' + order.hubResourceQuantity + '</div>' +
                    '<button  onClick="confirmOrder(' + order.orderId + ')" class="btn btn-warning" type="button" id="btn' +
                    order.orderId + '" >Confirm</button>' +
                    '</div>';
            });
            $('#orders').append(orders);
            page++;
        }
    });
}
function showConfirmed() {
    $.ajax({
        url: 'hubs/confirmedOrders?page=' + confirmedPage,
        dataType: 'json',
        cache: 'false',
        success: function (data) {
            let orders = '';
            $.each(data, function (i, order) {
                orders += '<div class= "row mb-3">' +
                    '<div class="col-1 themed-grid-col">' + order.orderId +
                    '</div><div class="col-1 themed-grid-col">' + order.locationName +
                    '</div><div class="col-4 themed-grid-col">' + order.userDescription +
                    '</div><div class="col-2 themed-grid-col">' + order.userPhone +
                    '</div><div class="col-2 themed-grid-col">' + order.resourceID +
                    '</div><div class="col-1 themed-grid-col">' + order.orderResourceQuantity +
                    '</div><div class="col-1 themed-grid-col">' + order.hubResourceQuantity + '</div>' +
                    '</div>';
            });
            $('#confirmed').append(orders);
            confirmedPage++;
        }
    });
}

function confirmOrder(orderId) {
    $.ajax({
        type: 'PUT',
        url: '/hubOrders/confirm/' + orderId,
        success: function () {
            let buttonId = 'btn' + orderId;
            document.getElementById(buttonId).className = "btn btn-success";
            document.getElementById(buttonId).disabled = true;
        }
    });
}

$(function () {
    showOrders();
    showConfirmed();
});