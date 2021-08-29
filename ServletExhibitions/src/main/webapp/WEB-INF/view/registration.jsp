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
    <title>Registration</title>
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
    request.setAttribute("email", View.view.getBundleText(ITextsPaths.EMAIL));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/">${home}</a>
    <a href="${pageContext.request.contextPath}/login">${login}</a>

</header>
<h2>${errorMessage}</h2>
<div class="form">

    <h1>${registration}</h1><br>
    <form method="post" action="">

        <input type="text" required placeholder="${username}" name="username"><br>
        <input type="password" required placeholder="${password}" name="password"><br>
        <input type="text" required placeholder="${email}" name="email"><br>
        <input class="button" type="submit" value="${registration}">

    </form>
</div>
</body>
</html>
