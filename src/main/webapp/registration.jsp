<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Быстрая регистрация</title>
    <link rel="stylesheet" href="static/styles/registration.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
</head>
<body>
    <form method="post" class="authorise" action="controller?command=registration">
        <h1>Быстрая регитрация</h1>
        <p>Если Вы уже зарегистрированы, перейдите на страницу <a href="login.jsp">входа в систему</a>.</p>
        <h2>ОСНОВНАЯ ИНФОРМАЦИЯ</h2>
        <ul class="list">
            <li>
                <label for="name">Имя:</label>
                <input class="shadow" id="name" type="text" name="name"/>
            </li>
            <li>
                <label for="surname">Фамилия:</label>
                <input class="shadow" id="surname" type="text" name="surname"/>
            </li>
            <li>
                <label for="login">Логин:</label>
                <input class="shadow" id="login" type="text" name="login"/>
            </li>
            <li>
                <label for="password">Пароль:</label>
                <input class="shadow" id="password" type="password" name="password"/>
            </li>
            <li>
                <label for="confirmedPassword">Подтвердите пароль:</label>
                <input class="shadow" id="confirmedPassword" type="password" name="confirmedPassword"/>
            </li>
        </ul>
        <div>
            <input class="checkbox" type="checkbox"> Я прочитал(а) и принимаю <a href="#popup">Условия пользовательского соглашения</a>
        </div>
        <div style="color: red">${errorRegistrationMessage}</div>
        <input type="submit" class="registration" value="Зарегестрироваться" name="agreement">
    </form>

    <div id="popup" class="popup">
        <a href="#" class="popup_area"></a>
        <div class="popup_body">
            <div class="popup_content">
                <a href="" class="popup_close">X</a>
                <div class="popup_title">Условия пользовательского соглашения</div>
                <div class="popup_text">
                    В целях предоставления сервиса Cafe.by Компания заключает договоры с Торговыми предприятиями и распространяет информацию о Торговых предприятиях и реализуемых ими товарах посредством Сайта и Приложений, которые доступны для каждого Клиента.
                    Клиент с использованием функционала Сайта или Приложения может оформить заказ товара Торгового предприятия. Задача Компании – передать информацию о Клиенте и сформированный им заказ соответствующему Торговому предприятию.
                    Настоящие Правила регулируют отношения Клиента и Компании.
                </div>
            </div>
        </div>
    </div>
</body>
</html>
