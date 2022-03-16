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
            <h1>${title_SC}</h1>
            <div class="content">
                <div class="items">
                    <div class="item">
                        <div class="col col1">
                            <a href="#" class="remove">${delete}</a>
                        </div>
                        <div class="col col3">
                            <div class="title">Название1</div>
                            <div class="prc">Цена: {5} р</div>
                        </div>
                        <div class="col col4">
                            <div class="q">
                                <a href="#" class="minus">-</a>
                                <input type="text" name="quantity" class="inputbox q_input" value="{2}">
                                <a href="#" class="plus">+</a>
                            </div>
                            <div class="price">× {5} р</div>
                            <div class="total">= {14} р</div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="col col1">
                            <a href="#" class="remove">Удалить</a>
                        </div>
                        <div class="col col3">
                            <div class="title">Название</div>
                            <div class="prc">Цена: {5} р</div>
                        </div>
                        <div class="col col4">
                            <div class="q">
                                <a href="#" class="minus">-</a>
                                <input type="text" name="quantity" class="inputbox q_input" value="{2}">
                                <a href="#" class="plus">+</a>
                            </div>
                            <div class="price">× {5} р</div>
                            <div class="total">= {14} р</div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="col col1">
                            <a href="#" class="remove">Удалить</a>
                        </div>
                        <div class="col col3">
                            <div class="title">Название</div>
                            <div class="prc">Цена: {5} р</div>
                        </div>
                        <div class="col col4">
                            <div class="q">
                                <a href="#" class="minus">-</a>
                                <input type="text" name="quantity" class="inputbox q_input" value="{2}">
                                <a href="#" class="plus">+</a>
                            </div>
                            <div class="price">× {5} р</div>
                            <div class="total">= {14} р</div>
                        </div>
                    </div>
                </div>
                <div class="right-cart">
                    <div class="confirm">
                        <div class="total">
                            ${price_text_start}<br>${price_text_end} {14} р
                        </div>
                        <div class="loyalty-score">
                            ${lbl_points} {11}
                        </div>
                        <div class="date">
                            <label for="date">${date_and_time}</label>
                            <input type="datetime" id="date">
                            <input type="datetime-local">
                        </div>
                        <div class="payment-method">
                            <div class="heading">${lbl_payment_method}</div>
                            <div class="content">
                                <div>
                                    <input type="radio" id="cash" name="money" checked>
                                    <label for="cash">${lbl_cash}</label>
                                </div>

                                <div>
                                    <input type="radio" id="account" name="money">
                                    <label for="account">${lbl_account}</label>
                                </div>
                            </div>

                        </div>
                        <div class="payment_btn">
                            <a href="#" class="button btn-green go">${lbl_pay}</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</section>
</body>
</html>
