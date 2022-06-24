<html lang="en">
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/analytics_lack.js"></script>
    <meta name="theme-color" content="#7952b3">
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
<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="mx-auto d-sm-flex d-block flex-sm-nowrap">
    <a class="navbar-brand" href="/analytics">Resources in stock</a>
        <a class="navbar-brand" href="/analyticsLack"><b>Lack of resources</b></a>
        <a class="navbar-brand" href="/analyticsTop">Top 10</a>
    </div>
</nav>
<h3 class="mt-4 text-center">Total lack resources to complete orders</h3>

<br>
<div class="container sticky-top">
    <div class="row mb-3  bg-info text-white">
        <div class="col-3 themed-grid-col"> resource id</div>
        <div class="col-6 themed-grid-col"> resource name</div>
        <div class="col-3 themed-grid-col"> quantity</div>
    </div>
</div>

<div id="resourcesOnHubs" class="container"></div>

<div class="container">
    <div class="row mb-3 align-items-center">
        <button onclick="showData()" class=" btn btn-info" type="button">Show more</button>
    </div>
    <a class="btn btn-outline-success" href="/export/xlsx" role="button">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-excel" viewBox="0 0 16 16">
            <path d="M5.884 6.68a.5.5 0 1 0-.768.64L7.349 10l-2.233 2.68a.5.5 0 0 0 .768.64L8 10.781l2.116 2.54a.5.5 0 0 0 .768-.641L8.651 10l2.233-2.68a.5.5 0 0 0-.768-.64L8 9.219l-2.116-2.54z"/>
            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
        </svg>
        Export Excel</a>
</div>
<#include "footer.ftlh">
</body>
</html>
