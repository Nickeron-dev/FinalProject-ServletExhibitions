<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html> <!--xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core"-->
<head>
    <title>Welcome</title>

</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("welcome", View.view.getBundleText(ITextsPaths.WELCOME));
    request.setAttribute("role", request.getSession().getAttribute("role"));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/login">login</a>
    <a href="${pageContext.request.contextPath}/registration">registration</a>
    <p>Current Role: ${role}</p>

    <a href="${pageContext.request.contextPath}/">${home}</a>
</header>
<h1>${welcome}</h1>
</body>
</html>
