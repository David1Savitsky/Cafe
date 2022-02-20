<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/styles/style.css">
</head>
<body>
    <div class="container">
        <form method="post" action="controller?command=login">
            <label class="login-row" for="login">login</label>
            <input id="login" class="login-row" type="text" name="login"/>
            <label class="login-row" for="password">password</label>
            <input id="password" class="login-row" type="password" name="password"/>
            <input class="login-row" type="submit"/>
        </form>
        <div style="color: red">${errorMessage}</div>
    </div>
</body>
</html>
