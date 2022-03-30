<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>
<fmt:message key="autorization" var="autorization"/>
<fmt:message key="lbl_login" var="lbl_login"/>
<fmt:message key="login" var="login"/>
<fmt:message key="password" var="password"/>
<fmt:message key="registrate" var="registrate"/>
<fmt:message key="enter" var="enter"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${autorization}</title>
    <link rel="stylesheet" href="static/styles/login.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
</head>
<body>
    <form method="post" class="login" action="controller?command=login">
        <h1>${autorization}</h1>
        <p>${lbl_login}</p>
        <div class="logpass">
            <label for="login">${login}:</label><br>
            <input class="shadow" id="login" type="text" name="login"/><br>
            <label for="password">${password}:</label><br>
            <input class="shadow" id="password" type="password" name="password"/>
        </div>
        <div style="color: red">${requestScope.errorMessage}</div>
        <a href="registration.jsp">${registrate}</a>
        <input type="submit" class="login_input" value="${enter}">
    </form>

</body>
</html>
