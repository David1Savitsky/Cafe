<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 05.04.2022
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:import url="header.jsp"/>

    <div class="container">
        <div class="wrapper">
            <h1 class="title">Личный кабинет</h1>
            <div class="pers">
                <div class="col50 side-left">
                    <div class="account-main-info">
                        <h3>Основная информация</h3>
                        <table class="row-info" width="100%">
                            <tr>
                                <td width="140"><strong>Имя</strong></td>
                                <td>Давид</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>Фамилия</strong></td>
                                <td>Еу</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>Логин</strong></td>
                                <td>dav</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>Роль</strong></td>
                                <td>Пользователь</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>Деньги на счету</strong></td>
                                <td>100</td>
                            </tr>
                            <tr>
                                <td width="140"><strong>Баллы лояльности</strong></td>
                                <td>80</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col40 side-right">
                    <div class="account-reward">
                        <div class="title">Бонусные баллы: </div>
                        <div class="total">107</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
