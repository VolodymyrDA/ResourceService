<!DOCTYPE html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">
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
</head>

<body class="text-center">
<main class="form-signin">
    <h1 class="h3 mb-3 fw-normal">Registration</h1>
    <div class="container">
        <form action="/registration" method="post">
            <div class="form-group"><label class="form-label"> Username : </label>
                <input class="form-control" type="text" name="username">
            </div>
            <div class="form-group"><label class="form-label"> Password: </label>
                <input class="form-control" type="password" name="password">
            </div>
            <div class="form-group"><label class="form-label"> Phone number :</label>
                <input class="form-control" type="text" name="phone">
            </div>
            <div class="form-group"><label class="form-label"> Description : </label>
                <textarea class="form-control" name="description"></textarea>
            </div>
            <div class="form-group"><label class="form-label"> Location : </label>
                <select class="form-control" type="text" name="locationId">
                    <#list locations as location>
                        <option value="${location.id}">${location.name}</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-group"><input class="btn  btn-info" type="submit" value="Register"/></div>
        </form>
    </div>
    <span class="text-muted"> <br> &copy; 2022 Cherkassy Geek, Inc</span>
</main>
</body>
</html>