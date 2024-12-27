<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>

</head>
<body>

<header class="navbar">
    <h1>Главная страница</h1>
    <nav>
        <c:if test="${empty sessionScope.userId}">
            <a href="<c:url value='/register'/>">Регистрация</a>
        </c:if>
        
        <a href="<c:url value='/profile' />">Мой профиль</a>
    </nav>
</header>
</body>
</html>
