<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/custom-tag/Paginator" prefix="paginator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/styles/style.css">
    <link rel="stylesheet" href="static/styles/paginator.css">
</head>
<body>
    <div class="container">
        <form method="post" action="controller?command=login">
            <label class="login-row" for="login">login</label>
            <input id="login" class="login-row" type="text" name="login"/>
            <label class="login-row" for="password">password</label>
            <input id="password" class="login-row" type="password" name="password"/>
            <input class="login-row" type="submit"/>
        </form>
        <div style="color: red">${errorMessage}</div>
    </div>
    <form action="controller?command=login" method="post">

    </form>
    <c:url var="searchUri" value="controller?command=loginpage=##"/>
    <paginator:display maxLinks="3" currPage="2" totalPages="3" uri="${searchUri}"/>
</body>
</html>
