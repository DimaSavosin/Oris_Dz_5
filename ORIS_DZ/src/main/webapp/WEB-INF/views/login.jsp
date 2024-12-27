<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.12.2024
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Почта:</label>
    <input type="email" name="email" id="email" required><br><br>
    <label for="password">Пароль:</label>
    <input type="password" name="password" id="password" required><br><br>
    <button type="submit">Войти</button>
</form>
${errorMessage}
</body>
</html>
