 <%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<fmt:message key="exit" var="btn_exit"/>
<fmt:message key="belorussianCode" var="bel"/>
<fmt:message key="englishCode" var="en"/>
<fmt:message key="ukrainianCode" var="uk"/>
<fmt:message key="lbl_language" var="lbl_lang"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/main.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>Заказать еду</title>
</head>
<body>
<div class="header">
    <img class="logo_image" src="static/images/logo.png" alt="">
    <h1 style="color:coral;">Cafe</h1>

    <div class="logandreg">
        <form method="post" action="controller?command=languageChange">
            <div class="dropdown">
                <button class="dropbtn">${lbl_lang}</button>
                <div class="dropdown-content">
                    <a><button type="submit" name="locale" value="bel">${bel}</button></a>
                    <a><button type="submit" name="locale" value="uk">${uk}</button></a>
                    <a><button type="submit" name="locale" value="en">${en}</button></a>
                </div>
            </div>
        </form>



        <a href="controller?command=logout"><input type="submit" class="login" value="${btn_exit}" ></a>
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
        <h2>Название</h2>
        <p>Его описание: выапавывпрпавовавовпоавапаро</p>
        <p>Цена</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>

    <div class="column side">
        <h2>Main Content</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>

    <div class="column side">
        <h2>Side</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>

    <div class="column side">
        <h2>Название</h2>
        <p>Его описание: выапавывпрпавовавовпоавапаро</p>
        <p>Цена</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>

    <div class="column side">
        <h2>Название</h2>
        <p>Его описание: выапавывпрпавовавовпоавапаро</p>
        <p>Цена</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>

    </div>

    <div class="column side">
        <h2>Название</h2>
        <p>Его описание: выапавывпрпавовавовпоавапаро</p>
        <p>Цена</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>

    <div class="column side">
        <h2>Название</h2>
        <p>Его описание: выапавывпрпавовавовпоавапаро</p>
        <p>Цена</p>
        <a href="#" class="btn_order"> Добавить в корзину</a>
    </div>
</div>


<div class="footer">
    <p>Cafe</p>
</div>
</body>
</html>
