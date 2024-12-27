<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label for="name">Имя:</label>
    <input id="name" name="name" type="text" required><br><br>
    <label for="email">Почта:</label>
    <input id="email" type="email" name="email" required><br><br>
    <label for="password">Пароль:</label>
    <input id="password" name="password" type="password" minlength="4" required><br><br>
    <button type="submit">Зарегистрироваться</button>
</form>
<p>${errorMessage}</p>
</body>
<form action="${pageContext.request.contextPath}/login" method="get">
    <button type="submit">Войти</button>
</form>
</html>
