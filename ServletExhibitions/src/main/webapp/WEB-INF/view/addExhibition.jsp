<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="java.time.LocalDate" %>
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
    request.setAttribute("now", LocalDate.now());
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

    <h1>Add exhibition</h1><br>
    <form method="post" action="">

        <label>Topic
            <input type="text" required placeholder="topic" name="topic">
        </label><br>
        <label>StartDate
            <input type="date" required placeholder="date" min="${now}" name="startDate">
        </label><br>
        <label>EndDate
            <input type="date" required placeholder="endDate" min="${now}" name="endDate">
        </label><br>
        <label>StartTime
            <input type="time" required placeholder="startTime" name="startTime">
        </label><br>
        <label>EndTime
            <input type="time" required placeholder="endTime" name="endTime">
        </label><br>
        <label>Rooms
            <input type="text" required placeholder="rooms" name="rooms">
        </label><br>
        <label>Price
            <input type="text" required placeholder="price" name="price">
        </label><br>
        <input class="button" type="submit" value="Add exhibition">

    </form>
</div>
</body>
</html>
