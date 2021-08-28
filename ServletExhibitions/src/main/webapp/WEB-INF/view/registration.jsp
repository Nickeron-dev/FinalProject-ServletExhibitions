<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
    <title>Login</title>

</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/login">login</a>

    <a href="${pageContext.request.contextPath}/">${home}</a>
</header>
<h2>${errorMessage}</h2>
<div class="form">

    <h1>Registration</h1><br>
    <form method="post" action="">

        <input type="text" required placeholder="login" name="username"><br>
        <input type="password" required placeholder="password" name="password"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <input class="button" type="submit" value="Register">

    </form>
</div>
</body>
</html>
