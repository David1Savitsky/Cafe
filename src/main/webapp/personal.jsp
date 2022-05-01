<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="lbl_personal" var="lbl_personal"/>
<fmt:message key="main_information" var="main_information"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="login" var="login"/>
<fmt:message key="role" var="role"/>
<fmt:message key="lbl_admin" var="lbl_admin"/>
<fmt:message key="lbl_user" var="lbl_user"/>
<fmt:message key="amount" var="amount"/>
<fmt:message key="loyalty_points" var="loyalty_points"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/personal.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${lbl_personal}</title>
</head>
<body>

    <c:import url="header.jsp"/>

    <div class="container">
        <div class="wrapper">
            <h1 class="title">${lbl_personal}</h1><br>
            <div class="pers">
                <div class="col50 side-left">
                    <div class="account-main-info">
                        <h3>${main_information}</h3>
                        <table class="row-info" width="100%">
                            <tr>
                                <td width="140"><strong>${name}</strong></td>
                                <td>${sessionScope.users.name}</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>${surname}</strong></td>
                                <td>${sessionScope.users.surname}</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>${login}</strong></td>
                                <td>${sessionScope.users.login}</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>${role}</strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.users.admin}">
                                            ${lbl_admin}
                                        </c:when>
                                        <c:otherwise>
                                            ${lbl_user}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td width="140"><strong>${amount}</strong></td>
                                <td>${sessionScope.users.amount} р</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col40 side-right">
                    <div class="account-reward">
                        <div class="title">${loyalty_points}: </div>
                        <div class="total">${sessionScope.users.loyaltyPoints}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
