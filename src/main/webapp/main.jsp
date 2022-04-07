<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="lbl_price" var="lbl_price"/>
<fmt:message key="btn_add_to_shopping_cart" var="btn_add_to_shopping_cart"/>
<fmt:message key="order_food" var="order_food"/>
<fmt:message key="items_not_found" var="items_not_found"/>
<fmt:message key="save_changes" var="save_changes"/>
<fmt:message key="name" var="food_name"/>
<fmt:message key="on_english" var="on_english"/>
<fmt:message key="btn_add_to_menu" var="btn_add_to_menu"/>
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
    <form action="controller?command=mainPage" method="post">
        <button type="submit">all</button>
    </form>
    <form method="post" action="controller?command=foodChange">
        <c:forEach items="${requestScope.foodTypeList}" var="type">
            <button type="submit" name="typeName" value="${type.id}">${type.name}</button>
        </c:forEach>
    </form>
</div>

<div class="row">
    <c:choose>
        <c:when test="${requestScope.foodListSize > 0}">
            <c:forEach items="${requestScope.foodList}" var="food">
                <div class="column side">
                    <c:choose>
                        <c:when test="${sessionScope.users.admin}">
                            <div>
                                <form method="post" action="controller?command=deleteFoodItem">
                                    <button class="remove" type="submit" name="foodId" value="${food.id}"></button>
                                </form>
                            </div>
                            <form method="post" action="controller?command=saveFoodItem">
                                <input class="elem" type="text" name="name" value="${food.name}">
                                <input type="hidden" name="typeId" value="${food.typeId}">
                                <p>${lbl_price}: <input class="elem2" type="number" name="price" value="${food.price}">р.</p>
                                <button class="btn_order" type="submit" name="foodId" value="${food.id}">${save_changes}</button>
                                <br>
                            </form>
                            <form action="controller?command=rating" method="post">
                                <button class="star" type="submit" name="foodId" value="${food.id}">★★★★★</button>
                            </form>

                        </c:when>
                        <c:otherwise>
                            <h2>${food.name}</h2>

                            <p>${lbl_price}: ${food.price} р.</p>
                            <form method="post" action="controller?command=addToShoppingCart">
                                <button class="btn_order" type="submit" name="foodId" value="${food.id}">${btn_add_to_shopping_cart}</button>
                                <br>
                            </form>
                            <form action="controller?command=rating" method="post">
                                <button class="star" type="submit" name="foodId" value="${food.id}">★★★★★</button>
                            </form>

                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1 style="color: red">${items_not_found}...</h1>
        </c:otherwise>
    </c:choose>
    <c:if test="${sessionScope.users.admin}">
        <div class="column side">
            <form method="post" action="controller?command=addFoodItem">
                <input class="elem" type="text" name="name" value="${food_name}">
                <p>${lbl_price}: <input class="elem2" type="number" name="price" value="0">р.</p>
                <p>Тип блюда: <input class="elem1" type="text" name="type" value="${on_english}"></p>

                <button class="btn_order" type="submit">${btn_add_to_menu}</button>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
