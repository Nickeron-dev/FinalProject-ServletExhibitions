<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>

<h1>Привіт ADMIN!</h1>
<a href="${pageContext.request.contextPath}/login">login</a>
<a href="${pageContext.request.contextPath}/registration">registration</a>
<a href="<c:url value='/logout' />">Logout</a>
</body>
</html>
