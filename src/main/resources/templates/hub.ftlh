<html lang="en">
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
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
    <link href="/css/headers.css" rel="stylesheet">
    <link href="/css/footers.css" rel="stylesheet">
    <link href="/css/grid.css" rel="stylesheet">
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
</head>

<body class="text-center">
<#include "header.ftlh">
<#assign
user = hub
phone = user.getPhone()
description = user.getDescription()
locationId= user.getLocationId()
>
<#if message??> <h3 class="display-4 text-success">${message}</h3></#if>
<h1 class="mt-4 text-center">Hub information</h1>

<div class="container">
    <form>
        <div class=" form-group"><label class="form-label"> Phone number :</label>
            <input class="form-control" type="text" name="phone" value="${phone}">
        </div>
        <div class="form-group"><label class="form-label"> Description : </label>
            <textarea class="form-control" name="description">${description}</textarea>
        </div>
        <div class="form-group"><label class="form-label"> Location : </label>
            <#list locations as location>
            <#if location.id==locationId ><input class="form-control" type="text" name="location" value="
            ${location.name}"></#if>
            </#list>
        </div>
        <br>
    </form>
</div>
<#include "footer.ftlh">
</body>
</html>