<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.view.View" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>

    <a href="${pageContext.request.contextPath}/registration">registration</a>

    <a href="${pageContext.request.contextPath}/">${home}</a>
</header>
    <div class="form">

        <h1>${login}</h1><br>
        <form method="post" action="">

            <input type="text" required placeholder="login" name="login"><br>
            <input type="password" required placeholder="password" name="password"><br><br>
            <input class="button" type="submit" value="${login}">

        </form>
    </div>
</body>
</html>
