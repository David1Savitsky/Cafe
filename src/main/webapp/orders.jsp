<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="lbl_orders" var="lbl_orders"/>
<fmt:message key="user_number" var="user_number"/>
<fmt:message key="lbl_date" var="lbl_date"/>
<fmt:message key="lbl_time" var="lbl_time"/>
<fmt:message key="lbl_content" var="lbl_content"/>
<fmt:message key="lbl_price" var="lbl_price"/>
<fmt:message key="lbl_payment" var="lbl_payment"/>
<fmt:message key="lbl_status" var="lbl_status"/>
<fmt:message key="paid" var="paid"/>
<fmt:message key="not_paid" var="not_paid"/>
<fmt:message key="took" var="took"/>
<fmt:message key="not_took" var="not_took"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/orders.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${lbl_orders}</title>
</head>
<body>

    <c:import url="header.jsp"/>

    <div class="container">
        <div class="wrapper">
            <h1 class="title">${lbl_orders}</h1>
            <div>
                <table>
                    <tr>
                        <c:if test="${sessionScope.users.admin}">
                            <th>${user_number}</th>
                        </c:if>
                        <th>${lbl_date}</th>
                        <th>${lbl_time}</th>
                        <th>${lbl_content}</th>
                        <th>${lbl_price}</th>
                        <th>${lbl_payment}</th>
                        <th>${lbl_status}</th>
<%--                        <th>Оценка</th>--%>

                    </tr>

                    <c:forEach items="${requestScope.orderList}" var="order">
                        <tr>
                            <c:if test="${sessionScope.users.admin}">
                                <td>${order.left.userId}</td>
                            </c:if>
                            <td>${order.left.date}</td>
                            <td>${order.left.time}</td>
                            <td>
                                <div>
                                    <c:forEach items="${order.middle}" var="food">
                                        <p>${food.name}</p>
                                    </c:forEach>
                                </div>
                            </td>
                            <td>${order.right}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.left.paymentType eq 'ACCOUNT'}">
                                        <p style="color: #10c90a;">${paid}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="color:red;">${not_paid}</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.left.taken}">
                                        <p style="color:#10c90a;">${took}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${sessionScope.users.admin}">
                                                <form action="controller?command=orderIsTaken" method="post">
                                                    <button class="check-mark" type="submit" name="orderId" value="${order.left.id}"></button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <p style="color:red;">${not_took}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </td>



<%--                            <td>--%>
                                 <%-- <form class="rating-area" action="controller?command=changeRat" method="get">--%>
<%--                                         <p>${order.left.id}</p>--%>
     <%--                                    <input type="hidden" name="rating" value="${4}">--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${order.left.rating eq 5}">--%>
<%--                                            <input type="radio" id="star-5" name="rating" value="5" checked>
<%--                                            <label for="star-5" title="Оценка «5»"></label>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <input type="radio" id="star-5" name="rating" value="5">--%>
<%--                                            <label for="star-5" title="Оценка «5»"></label>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${order.left.rating eq 4}">--%>
<%--                                            <input type="radio" id="star-4" name="rating" value="4" checked>--%>
<%--                                            <label for="star-4" title="Оценка «4»"></label>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <input type="radio" id="star-4" name="rating" value="4">--%>
<%--                                            <label for="star-4" title="Оценка «4»"></label>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${order.left.rating eq 3}">--%>
<%--                                            <input type="radio" id="star-3" name="rating" value="3" checked>--%>
<%--                                            <label for="star-3" title="Оценка «3»"></label>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <input type="radio" id="star-3" name="rating" value="3">--%>
<%--                                            <label for="star-3" title="Оценка «3»"></label>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${order.left.rating eq 2}">--%>
<%--                                            <input type="radio" id="star-2" name="rating" value="2" checked>--%>
<%--                                            <label for="star-2" title="Оценка «2»"></label>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <input type="radio" id="star-2" name="rating" value="2">--%>
<%--                                            <label for="star-2" title="Оценка «2»"></label>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${order.left.rating eq 1}">--%>
<%--                                            <input type="radio" id="star-1" name="rating" value="1" checked>--%>
<%--                                            <label for="star-1" title="Оценка «1»"></label>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <input type="radio" id="star-1" name="rating" value="1">--%>
<%--                                            <label for="star-1" title="Оценка «1»"></label>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
                                         <%--<button type="submit" name="orderId" value="${order.left.id}">Отправить</button>--%>
                        <%--</form>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</div>

</body>
</html>
