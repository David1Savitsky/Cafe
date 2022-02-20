<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" href="static/styles/login.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
</head>
<body>
    <form method="post" class="login" action="controller?command=login">
        <h1>Авторизация</h1>
        <p>Войти в Личный Кабинет</p>
        <div class="logpass">
            <label for="login">Логин:</label><br>
            <input class="shadow" id="login" class="login-row" type="text" name="login"/><br>
            <label for="password">Пароль:</label><br>
            <input class="shadow" id="password" type="password" name="password"/>
        </div>
        <div style="color: red">${errorMessage}</div>
        <a href="registration.jsp">Зарегестрироваться</a>
        <input type="submit" class="login_input" value="Войти">
    </form>

</body>
</html>
