<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="bel" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<fmt:message key="no_comments" var="no_comments"/>
<fmt:message key="lbl_leave_comment" var="lbl_leave_comment"/>
<fmt:message key="lbl_send" var="lbl_send"/>
<fmt:message key="lbl_all_comments" var="lbl_all_comments"/>
<fmt:message key="lbl_rating" var="lbl_rating"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/rating.css">
    <link rel="shortcut icon" href="static/images/address.png" type="image/png">
    <title>${lbl_rating}</title>
</head>
<body>

    <c:import url="header.jsp"/>

    <div class="container1">
        <div class="wrapper">
            <h1 class="title">${requestScope.food.name}</h1>
            <c:if test="${!sessionScope.users.admin}">
                <div class="comments_stars">
                    <form action="controller?command=addComment" method="post" class="your_comment">
                        <p>${lbl_leave_comment}</p>
                        <input type="hidden" name="foodId" value="${foodId}">
                        <textarea name="comment" id="" cols="40" rows="5" placeholder="..."></textarea><br><br>
                        <button type="submit" class="button"> ${lbl_send}</button>
                    </form>

                    <div class="stars">
                        <form class="rating-area" action="controller?command=changeRating" method="post">
                            <input type="hidden" name="foodId" value="${foodId}">
                            <c:choose>
                                <c:when test="${rating.rating eq 5}">
                                    <input type="radio" id="star-5"  checked>
                                    <label for="star-5" title="Оценка «5»"></label>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" id="star-5" name="rating" value="5">
                                    <label for="star-5" title="Оценка «5»"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${rating.rating eq 4}">
                                    <input type="radio" id="star-4" checked>
                                    <label for="star-4" title="Оценка «4»"></label>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" id="star-4" name="rating" value="4">
                                    <label for="star-4" title="Оценка «4»"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${rating.rating eq 3}">
                                    <input type="radio" id="star-3" checked>
                                    <label for="star-3" title="Оценка «3»"></label>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" id="star-3" name="rating" value="3">
                                    <label for="star-3" title="Оценка «3»"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${rating.rating eq 2}">
                                    <input type="radio" id="star-2" checked>
                                    <label for="star-2" title="Оценка «2»"></label>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" id="star-2" name="rating" value="2">
                                    <label for="star-2" title="Оценка «2»"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${rating.rating eq 1}">
                                    <input type="radio" id="star-1" checked>
                                    <label for="star-1" title="Оценка «1»"></label>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" id="star-1" name="rating" value="1">
                                    <label for="star-1" title="Оценка «1»"></label>
                                </c:otherwise>
                            </c:choose>
                        </form>

                    </div>
                </div>
            </c:if>




            <br>


            <div class="comments_wrapper">
                <c:choose>
                    <c:when test="${requestScope.commentListSize > 0}">
                        <h1>${lbl_all_comments}</h1>
                        <c:forEach items="${requestScope.commentList}" var="comment">
                            <div class="container">
                                <c:if test="${sessionScope.users.admin}">
                                        <form method="post" action="controller?command=deleteCommentItem">
                                        <button class="remove" type="submit" name="commentId" value="${comment.value.id}"></button>
                                    </form>
                                </c:if>
                                <p><span>${comment.key.name}</span> ${comment.key.login}</p><br>
                                <p>${comment.value.comment}.</p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h1 style="color: red">${no_comments}...</h1>
                    </c:otherwise>
                </c:choose>


            </div>
        </div>
    </div>
</body>
</html>
