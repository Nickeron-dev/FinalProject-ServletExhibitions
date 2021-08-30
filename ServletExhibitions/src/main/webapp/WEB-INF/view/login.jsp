<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.view.View" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            text-align: center;
        }

        header {
            padding-bottom: 3em;
            background: yellow;
        }

        a {
            float: left;
            padding: 15px;
            color: black;
            font-size: 1em;
            text-decoration: none;
        }

        a:hover {
            background: darkkhaki;
        }

        header input {
            float: right;
        }
    </style>
</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
    request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));

    request.setAttribute("username", View.view.getBundleText(ITextsPaths.USERNAME));
    request.setAttribute("password", View.view.getBundleText(ITextsPaths.PASSWORD));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/">${home}</a>
    <a href="${pageContext.request.contextPath}/registration">${registration}</a>

</header>
<h2>${errorMessage}</h2>
    <div class="form">

        <h1>${login}</h1><br>
        <form method="post" action="">

            <input type="text" required placeholder="${username}" name="login"><br>
            <input type="password" required placeholder="${password}" name="password"><br><br>
            <input class="button" type="submit" value="${login}">

        </form>
    </div>
</body>
</html>
