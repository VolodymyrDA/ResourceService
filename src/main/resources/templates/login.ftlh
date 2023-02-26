<!DOCTYPE html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">
</head>
<body class="text-center">
<main class="form-signin">
    <#if message??> <h3 class="display-4 text-success">${message}</h3></#if>

    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
    <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
        <p class="text-danger">The login or password you have entered is invalid, try again.</p>
    </#if>
    <div class="container">
        <form action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label class="form-label"> User Name : </label>
                <input class="form-control" type="text" name="username"/>
            </div>
            <div class="form-group"><label class="form-label"> Password:</label>
                <input class="form-control" type="password" name="password"/></div>
            <div class="form-group"><input class="btn btn-info" type="submit" value="Sign In"/></div>
            <br>
            <div class="form-group" style="text-align: right">
                <a class="link-warning" href="/registration">Registration</a>
            </div>
        </form>

        <br>

        <div class="form-group">
            <a class="btn btn-outline-primary" href="/oauth2/authorization/google">Sign in with Google</a>
        </div>
    </div>
    <span class="text-muted"> <br> &copy; 2022 Cherkassy Geek, Inc</span>
</main>
</body>
</html>
