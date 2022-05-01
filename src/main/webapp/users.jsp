<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="lbl_users" var="lbl_users"/>
<fmt:message key="name" var="user_name"/>
<fmt:message key="surname" var="user_surname"/>
<fmt:message key="login" var="user_login"/>
<fmt:message key="role" var="user_role"/>
<fmt:message key="amount" var="user_amount"/>
<fmt:message key="loyalty_points" var="user_loyalty_points"/>
<fmt:message key="lbl_admin" var="lbl_admin"/>
<fmt:message key="lbl_user" var="lbl_user"/>
<fmt:message key="save_changes" var="save_changes"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/users.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${lbl_users}</title>
</head>
<body>
    <c:import url="header.jsp"/>

    <div class="container">
        <div class="wrapper">
            <h1 class="title">${lbl_users}</h1><br>

            <div class="table">
                <table>
                    <tr>
                        <th>${user_name}</th>
                        <th>${user_surname}</th>
                        <th>${user_login}</th>
                        <th>${user_role}</th>
                        <th>${user_amount}</th>
                        <th></th>
                        <th>${user_loyalty_points}(0..100)</th>
                    </tr>
                        <c:forEach items="${requestScope.userList}" var="user">
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.surname}</td>
                                <td>${user.login}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.admin}">
                                            ${lbl_admin}
                                        </c:when>
                                        <c:otherwise>
                                            ${lbl_user}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.amount} р</td>
<%--                                <td>--%>
<%--                                    <input class="points" type="number" value="${user.loyaltyPoints}">--%>
<%--                                </td>--%>
                                <td>
                                    <form class="locks" action="controller?command=changeBlock" method="post">
                                        <c:choose>
                                            <c:when test="${user.blocked}">
                                                <button class="closed_lock" type="submit" name="userId" value="${user.id}"></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="opened_lock" type="submit" name="userId" value="${user.id}"></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                                <form class="payment_btn" action="controller?command=changeLoyaltyPoints" method="post">
                                    <td>
                                        <input class="points" type="number" name="loyaltyPoints" value="${user.loyaltyPoints}">
                                    </td>
                                    <td>
                                        <button class="button btn-green go" type="submit" name="userId" value="${user.id}">${save_changes}</button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>

                </table>
            </div>
        </div>
    </div>
</body>
</html>
