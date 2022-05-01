<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="quick_registration" var="quick_registration"/>
<fmt:message key="start_text" var="start_text"/>
<fmt:message key="end_text" var="end_text"/>
<fmt:message key="main_information" var="main_information"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="login" var="login"/>
<fmt:message key="password" var="password"/>
<fmt:message key="confirmed_password" var="confirmed_password"/>
<fmt:message key="start_acceptance" var="start_acceptance"/>
<fmt:message key="end_acceptance" var="end_acceptance"/>
<fmt:message key="registrate" var="registrate"/>
<fmt:message key="rules_content" var="rules_content"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${quick_registration}</title>
    <link rel="stylesheet" href="static/styles/registration.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
</head>
<body>
    <form method="post" class="authorise" action="controller?command=registration">
        <h1>${quick_registration}</h1>
        <p>${start_text}
            <a href="<c:url value="controller?command=loginPage"/>">${end_text}</a>
        .</p>

        <h2>${main_information}</h2>
        <ul class="list">
            <li>
                <label for="name">${name}:</label>
                <input class="shadow" id="name" type="text" name="name"/>
            </li>
            <li>
                <label for="surname">${surname}:</label>
                <input class="shadow" id="surname" type="text" name="surname"/>
            </li>
            <li>
                <label for="login">${login}:</label>
                <input class="shadow" id="login" type="text" name="login"/>
            </li>
            <li>
                <label for="password">${password}:</label>
                <input class="shadow" id="password" type="password" name="password"/>
            </li>
            <li>
                <label for="confirmedPassword">${confirmed_password}:</label>
                <input class="shadow" id="confirmedPassword" type="password" name="confirmedPassword"/>
            </li>
        </ul>
        <div>
            <input class="checkbox" type="checkbox" name="rules" value="yes"> ${start_acceptance} <a href="#popup">${end_acceptance}</a>
        </div>
        <div style="color: red">${requestScope.errorRegistrationMessage}</div>
        <input type="submit" class="registration" value="${registrate}" name="agreement">
    </form>

    <div id="popup" class="popup">
        <a href="#" class="popup_area"></a>
        <div class="popup_body">
            <div class="popup_content">
                <a href="" class="popup_close">X</a>
                <div class="popup_title">${end_acceptance}</div>
                <div class="popup_text">${rules_content}.</div>
            </div>
        </div>
    </div>
</body>
</html>
