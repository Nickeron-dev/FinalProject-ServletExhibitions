<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta content="text/html">
    <title>Statistics</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/statistics.css" type="text/css">
</head>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("welcome", View.view.getBundleText(ITextsPaths.WELCOME));
    request.setAttribute("role", request.getSession().getAttribute("role"));
    request.setAttribute("topic", View.view.getBundleText(ITextsPaths.TOPIC));
    request.setAttribute("startDate", View.view.getBundleText(ITextsPaths.START_DATE));
    request.setAttribute("endDate", View.view.getBundleText(ITextsPaths.END_DATE));
    request.setAttribute("startTime", View.view.getBundleText(ITextsPaths.START_TIME));
    request.setAttribute("endTime", View.view.getBundleText(ITextsPaths.END_TIME));
    request.setAttribute("price", View.view.getBundleText(ITextsPaths.PRICE));
    request.setAttribute("rooms", View.view.getBundleText(ITextsPaths.ROOMS));
    request.setAttribute("state", View.view.getBundleText(ITextsPaths.STATE));
    request.setAttribute("buyTicket", View.view.getBundleText(ITextsPaths.BUY_A_TICKET));
    request.setAttribute("buy", View.view.getBundleText(ITextsPaths.BUY));
    request.setAttribute("cancel", View.view.getBundleText(ITextsPaths.CANCEL));
    request.setAttribute("plan", View.view.getBundleText(ITextsPaths.PLAN));
    request.setAttribute("visitors", View.view.getBundleText(ITextsPaths.VISITORS));
    request.setAttribute("noElementsFound", View.view.getBundleText(ITextsPaths.ELEMENTS_NOT_FOUND));
%>
<body>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/login">login</a>
    <a href="${pageContext.request.contextPath}/registration">registration</a>
</header>


<table border="2">
    <tr>
        <th>${topic}</th>
        <th>${startDate}</th>
        <th>${endDate}</th>
        <th>${price}</th>
        <th>${state}</th>
        <th>${visitors}</th>
    </tr>
    <c:forEach var="item" items="${statistics}">
        <tr>
            <td>${item.getTopic()}</td>
            <td>${item.startDate}</td>
            <td>${item.endDate}</td>
            <td>${item.price}</td>
            <td>${item.state}</td>
            <td>${item.visitors}</td>
        </tr>
    </c:forEach>
</table>

</body>

</html>
