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
<fmt:message key="lbl_shopping_cart" var="lbl_shop_cart"/>
<fmt:message key="lbl_users" var="lbl_users"/>
<fmt:message key="top_up" var="top_up"/>
<fmt:message key="rechange_account_money" var="rechange_account_money"/>
<fmt:message key="card_number" var="card_number"/>
<fmt:message key="rechange" var="rechange"/>
<fmt:message key="sum" var="sum"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/header.css">
</head>
<body>
<header>
    <div class="container">
        <div class="menu_wrapper">
            <div class="menu_item">
                <a href="#"><input type="submit" class="logout" value="${lbl_shop_cart}" ></a>
                <a href="#"><input type="submit" class="logout" value="${lbl_users}" ></a>
            </div>
            <div class="menu_item">
                <a href="<c:url value="controller?command=mainPage"/>">
                    <img class="logo_image" src="static/images/logo.png" alt="">
                </a>
            </div>
            <div class="menu_item">
                <div class="button">
                    <form method="post" action="controller?command=languageChange">
                        <div class="dropdown">
                            <button class="dropbtn">${lbl_lang}</button>
                            <div class="dropdown-content">
                                <button type="submit" name="locale" value="bel">${bel}</button>
                                <button type="submit" name="locale" value="uk">${uk}</button>
                                <button type="submit" name="locale" value="en">${en}</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="button">
                    <div class="dropdown">
                        <button class="cash">${requestScope.accountMoney} р</button>
                        <div class="dropdown-content">
                            <button type="submit" name="#" value="#"><a href="#popup">${top_up}</a></button>
                        </div>
                    </div>
                </div>
                <div class="button">
                    <a href="controller?command=logout"><input type="submit" class="logout" value="${btn_exit}" ></a>
                </div>
            </div>
        </div>
    </div>
    <div id="popup" class="popup">
        <a href="#" class="popup_area"></a>
        <div class="popup_body">
            <div class="popup_content">
                <a href="" class="popup_close">X</a>
                <div class="popup_title">${rechange_account_money}</div>
                <div class="popup_text">
                    <form method="post" class="fillUpMoney" action="controller?command=fillUpMoney">
                        <label for="сard">${card_number}:</label><br>
                        <input class="shadow" id="card" type="number" name="card"/><br>
                        <label for="money">${sum}:</label><br>
                        <input class="shadow" id="money" type="number" name="money"/>
                        <div style="color: red">${sessionScope.errorFillUp}</div>
                        <input type="submit" class="logout" value="${rechange}">
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>
