<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>
<c:if test="${sessionScope.q_input == null}">
    <c:set var="q_input" value="1"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<fmt:message key="lbl_price" var="lbl_price"/>
<fmt:message key="btn_add_to_shopping_cart" var="btn_add_to_shopping_cart"/>
<fmt:message key="drop_meal" var="drop_meal"/>
<fmt:message key="drop_drink" var="drop_drink"/>
<fmt:message key="order_food" var="order_food"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/main.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${order_food}</title>
</head>
<body>

<c:import url="header.jsp"/>

<div class="topnav">
    <form method="post" action="controller?command=foodChange">
        <button type="submit" name="foodType" value="drink">${drop_drink}</button>
        <button type="submit" name="foodType" value="meal">${drop_meal}</button>
    </form>
</div>

<div class="row">

    <c:choose>
        <c:when test="${requestScope.foodListSize > 0}">
            <c:forEach items="${requestScope.foodList}" var="food">
                <div class="column side">
                    <h2>${ food.name }</h2>
                    <p>${lbl_price}: ${ food.price } р.</p>
                    <div class="col col4">
                        <div class="q">
                            <form method="post" action="controller?command=changeQIndex">
                                <button type="submit" name="type" value="minus"><a class="minus">-</a></button>
                                <input type="text" name="quantity" class="inputbox q_input" value="${q_input}">
                                <button type="submit" name="type" value="plus"><a class="plus">+</a></button>
                            </form>
                        </div>
                    </div>
                    <a href="#" class="btn_order">${btn_add_to_shopping_cart}</a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1 style="color: red">Menu items not found...</h1>
        </c:otherwise>
    </c:choose>
<%--    <div class="column side">--%>
<%--        <h2>Название</h2>--%>
<%--        <p>Его описание: выапавывпрпавовавовпоавапаро</p>--%>
<%--        <p>Цена</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Main Content</h2>--%>
<%--        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Side</h2>--%>
<%--        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Название</h2>--%>
<%--        <p>Его описание: выапавывпрпавовавовпоавапаро</p>--%>
<%--        <p>Цена</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Название</h2>--%>
<%--        <p>Его описание: выапавывпрпавовавовпоавапаро</p>--%>
<%--        <p>Цена</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>

<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Название</h2>--%>
<%--        <p>Его описание: выапавывпрпавовавовпоавапаро</p>--%>
<%--        <p>Цена</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>

<%--    <div class="column side">--%>
<%--        <h2>Название</h2>--%>
<%--        <p>Его описание: выапавывпрпавовавовпоавапаро</p>--%>
<%--        <p>Цена</p>--%>
<%--        <a href="#" class="btn_order"> Добавить в корзину</a>--%>
<%--    </div>--%>
</div>

</body>
</html>
