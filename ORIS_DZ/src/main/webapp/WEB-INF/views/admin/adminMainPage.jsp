<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ-панель</title>
</head>
<body>
<h1>Добро пожаловать в админ-панель!</h1>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/users">Управление пользователями</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/bookings">Актуальные бронирования</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/bookingHistory">История бронирований</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/addMenuItem">Добавить блюдо</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/tables">Добавить стол</a></li>
    </ul>
</nav>

</body>
</html>
