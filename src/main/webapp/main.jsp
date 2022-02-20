<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="language"
       value="en"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/styles/main.css">
        <link rel="shortcut icon" href="static/images/address.png" type="image/png">
        <title>Заказать еду</title>
    </head>
    <body>
        <div class="header">
            <img class="logo_image" src="static/images/logo.png" alt="">
            <h1 style="color:coral;">Cafe</h1>

            <div class="dropdown">
                <button class="dropbtn">Русский</button>
                <div class="dropdown-content">
                    <a href="#">Белорусский</a>
                    <a href="#">Английский</a>
                </div>
            </div>

            <div class="logandreg">
<%--                <a href="login.jsp"><input type="submit" class="login" value=<fmt:message key="exit">></a>--%>
                <a href="login.jsp"><input type="submit" class="login" value="Выход" ></a>
    <%--            или--%>
    <%--            <a href="#" class="register"> Зарегистрироваться</a>--%>

                <a href="#"><span class="cash">123 р</span></a>
            </div>
        </div>

        <div class="content">
        </div>

        <div class="topnav">
            <a href="#">Пицца</a>
            <a href="#">Шаурма</a>
            <a href="#">Кофе</a>
        </div>

        <div class="row">
            <div class="column side">
                <h2>Side</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
            </div>

            <div class="column middle">
                <h2>Main Content</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>
            </div>

            <div class="column side">
                <h2>Side</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
            </div>
        </div>

        <div class="footer">
            <p>Cafe</p>
        </div>
    </body>
</html>
