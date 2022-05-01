<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="title_shopping_cart" var="title_SC"/>
<fmt:message key="SC_delete" var="delete"/>
<fmt:message key="price_text_start" var="price_text_start"/>
<fmt:message key="price_text_end" var="price_text_end"/>
<fmt:message key="lbl_points" var="lbl_points"/>
<fmt:message key="date_and_time" var="date_and_time"/>
<fmt:message key="lbl_payment_method" var="lbl_payment_method"/>
<fmt:message key="lbl_cash" var="lbl_cash"/>
<fmt:message key="lbl_account" var="lbl_account"/>
<fmt:message key="lbl_pay" var="lbl_pay"/>
<fmt:message key="empty_shop_cart" var="empty_shop_cart"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/shopping_cart.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${title_SC}</title>
</head>
<body>

<c:import url="header.jsp"/>

<section>
    <div class="container">
        <div class="wrapper">
            <c:choose>
                <c:when test="${requestScope.foodListSize > 0}">
                    <h1>${title_SC}</h1>
                    <div class="content">
                        <div class="items">
                            <c:forEach items="${requestScope.foodList}" var="food">
                                <div class="item">
                                    <div class="col col1">
                                        <form method="post" action="controller?command=deleteFromShopCart">
                                            <button class="remove" type="submit" name="foodId" value="${food.key.id}">${delete}</button>
                                        </form>
                                    </div>
                                    <div class="col col3">
                                        <div class="title">${food.key.name}</div>
                                        <div class="prc">Цена: ${food.key.price} р</div>
                                    </div>
                                    <div class="col col4">
                                        <div class="q">
                                            <form method="post" action="controller?command=decrementFoodInShopCart">
                                                <button class="minus" type="submit" name="foodId" value="${food.key.id}">-</button>
                                                <input type="text" name="quantity" class="inputbox q_input" readonly value="${food.value}">
                                            </form>

                                            <form method="post" action="controller?command=incrementFoodInShopCart">
                                                <button class="plus" type="submit" name="foodId" value="${food.key.id}">+</button>
                                            </form>
                                        </div>
                                        <div class="price">× ${food.key.price} р</div>
                                        <div class="total">= ${food.value * food.key.price} р</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="right-cart">
                            <form class="confirm" method="post" action="controller?command=payOrder">
                                <div class="total">
                                    ${price_text_start}<br>${price_text_end} ${requestScope.totalAmount} р
                                    <input type="hidden" name="total" value="${requestScope.totalAmount}">
                                </div>
                                <div class="loyalty-score">
                                        ${lbl_points} ${requestScope.totalAmount}<br>
                                </div>
                                <div class="date">
                                    <label for="date">${date_and_time}</label>
                                    <input type="datetime-local" name="date" id="date">
                                </div>
                                <div class="payment-method">
                                    <div class="heading">${lbl_payment_method}</div>
                                    <div class="content">
                                        <div>
                                            <input type="radio" id="cash" name="type" value="cash" checked>
                                            <label for="cash">${lbl_cash}</label>
                                        </div>

                                        <div>
                                            <input type="radio" id="account" name="type" value="account">
                                            <label for="account">${lbl_account}</label>
                                        </div>
                                    </div>
                                    <div style="color: red">${sessionScope.orderError}</div>
                                </div>
                                <div class="payment_btn">
                                    <button class="button btn-green go" type="submit">${lbl_pay}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1 style="color: gray">${empty_shop_cart}</h1>
                </c:otherwise>
            </c:choose>


        </div>

    </div>
</section>
</body>
</html>
