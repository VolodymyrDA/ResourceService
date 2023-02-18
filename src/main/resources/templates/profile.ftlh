<html lang="en">
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
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

<body class="text-center">
<#include "header.ftlh">
<#assign
user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
username = user.getUsername()
phone = user.getPhone()
description = user.getDescription()
locationId= user.getLocationId()
>
<#if message??> <p class="text-success">${message}</p></#if>
<#if errors??>
    <#list errors as error>
        <p class="text-danger">${error.getDefaultMessage()}</p>
    </#list>
</#if>
<h1 class="mt-4 text-center">Profile</h1>

<div class="container">
    <form action="/profile" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group"><label class="form-label"> User Name : </label>
            <input class="form-control" type="text" name="userName" value="${username}">
        </div>
        <div class="form-group"><label class="form-label"> Password: </label>
            <input class="form-control" type="password" name="password">
        </div>
        <div class=" form-group"><label class="form-label"> Phone number :</label>
            <input class="form-control" type="text" name="phone" value="${phone}">
        </div>
        <div class="form-group"><label class="form-label"> Description : </label>
            <textarea class="form-control" name="description">${description}</textarea>
        </div>
        <div class="form-group"><label class="form-label"> Location : </label>
            <select class="form-control" type="text" name="locationId" value="${locationId}">
                <#list locations as location>
                    <option value="${location.id}" <#if location.id==locationId > selected="${location.id}" </#if>
                    >${location.name}</option>
                </#list>
            </select>
        </div>
        <br>
        <div class="form-group"><input class="btn  btn-info" type="submit" value="Update"/></div>
    </form>
</div>
<#include "footer.ftlh">
</body>
</html>