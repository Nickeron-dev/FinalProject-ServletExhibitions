<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.view.View" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
        <%
                request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));

                request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
                request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));
                request.setAttribute("result", View.view.getBundleText(ITextsPaths.BUY_TICKET_SUCCESS));
        %>
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
<header>
        <form action="${pageContext.request.contextPath}/" method="post">
                <input type="submit" name="ukr" value="UKR">
                <input type="submit" name="eng" value="ENG">
        </form>
        <a href="${pageContext.request.contextPath}/">${home}</a>
        <a href="${pageContext.request.contextPath}/login">${login}</a>
        <a href="${pageContext.request.contextPath}/registration">${registration}</a>
</header>
<p>${result}</p>
</body>
</html>
