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
                    <a href="#"><span class="cash">123 р</span></a>
                </div>
                <div class="button">
                    <a href="controller?command=logout"><input type="submit" class="logout" value="${btn_exit}" ></a>
                </div>

            </div>
        </div>
    </div>
</header>
</body>
</html>
