<html lang="en">
<head>
    <title>Hub orders</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/HubsOrders.js"></script>
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
<h3 class="mt-4 text-center">Orders request </h3>

<br>
<div class="container sticky-top">
    <div class="row mb-3  bg-info text-white">
        <div class="col-1 themed-grid-col"> order id</div>
        <div class="col-1 themed-grid-col"> location</div>
        <div class="col-4 themed-grid-col"> user</div>
        <div class="col-2 themed-grid-col"> phone</div>
        <div class="col-2 themed-grid-col"> resource id</div>
        <div class="col-1 themed-grid-col"> quantity</div>
        <div class="col-1 themed-grid-col"> in stock</div>
    </div>
</div>

<div id="orders" class="container">
</div>

<div class="container">
    <div class="row mb-3 align-items-center">
        <button onclick="showOrders()" class=" btn btn-info" type="button">Show more</button>
    </div>
</div>
<h3 class="mt-4 text-center">Confirmed orders </h3>
<br>
<div class="container sticky-top">
    <div class="row mb-3  bg-info text-white">
        <div class="col-1 themed-grid-col"> order id</div>
        <div class="col-1 themed-grid-col"> location</div>
        <div class="col-4 themed-grid-col"> user</div>
        <div class="col-2 themed-grid-col"> phone</div>
        <div class="col-2 themed-grid-col"> resource id</div>
        <div class="col-1 themed-grid-col"> quantity</div>
        <div class="col-1 themed-grid-col"> in stock</div>
    </div>
</div>

<div id="confirmed" class="container">
</div>

<div class="container">
    <div class="row mb-3 align-items-center">
        <button onclick="showConfirmed()" class=" btn btn-info" type="button">Show more</button>
    </div>
</div>

<#include "footer.ftlh">
</body>
</html>