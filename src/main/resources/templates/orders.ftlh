<html lang="en">
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/orders.js"></script>
    <meta name="theme-color" content="#7952b3">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="css/headers.css" rel="stylesheet">
    <link href="css/footers.css" rel="stylesheet">
    <link href="css/grid.css" rel="stylesheet">
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
</head>

<body>
<#include "header.ftlh">
<h3 class="mt-4 text-center">Place order request</h3>
<hr>
<h4 class="mt-4 text-center">input item and quantity</h4>
<div class="container">
    <select class="form-select" aria-label="select categories" id="categories">
        <option>select categories</option>
    </select>
    <br>
    <select class="form-select" aria-label="select subcategories" id="subcategories">
        <option>select subcategories</option>
    </select>
    <br>
    <select class="form-select" aria-label="select resources" id="resources">
        <option>select resource</option>
    </select>
    <br>
    <div class="mb-3">
        <input type="text" id="quantity" name="quantity" class="form-control" placeholder="input quantity">
    </div>
    <div class="row mb-3 align-items-center">
        <button type="submit" class="btn btn-info" onclick="submitOrder()">Send</button>
    </div>
</div>
</div>
<h3 class="mt-4 text-center">Orders</h3>

<br>
<div class="container sticky-top">
    <div class="row mb-3  bg-info text-white">
        <div class="col-1 themed-grid-col"> order id</div>
        <div class="col-1 themed-grid-col"> resource id</div>
        <div class="col-5 themed-grid-col"> resource name</div>
        <div class="col-2 themed-grid-col"> quantity</div>
        <div class="col-3 themed-grid-col"> status</div>
    </div>
</div>

<div id="orders" class="container"></div>

<div class="container">
    <div class="row mb-3 align-items-center">
        <button onclick="showOrders()" class=" btn btn-info" type="button">Show more</button>
    </div>
</div>
<#include "footer.ftlh">
</body>
</html>
