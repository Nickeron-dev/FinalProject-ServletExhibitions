<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
    <title>Add exhibition</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" accept-charset="UTF-8"/>
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

        div {
            margin-right: auto;
            margin-left: auto;
            width: 200px;
            border: aqua;
            height: 500px;
        }

    </style>
</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("now", LocalDate.now());
    request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
    request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));
    request.setAttribute("statistics", View.view.getBundleText(ITextsPaths.STATISTICS_HREF));
    request.setAttribute("addExhibition", View.view.getBundleText(ITextsPaths.ADD_EXHIBITION_HREF));

    request.setAttribute("topic", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_TOPIC));
    request.setAttribute("startDate", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_START_DATE));
    request.setAttribute("endDate", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_END_DATE));
    request.setAttribute("startTime", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_START_TIME));
    request.setAttribute("endTime", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_END_TIME));
    request.setAttribute("price", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_PRICE));
    request.setAttribute("rooms", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_ROOMS_NUMBER));
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/">${home}</a>
    <a href="${pageContext.request.contextPath}/login">${login}</a>
    <a href="${pageContext.request.contextPath}/registration">${registration}</a>
    <a href="${pageContext.request.contextPath}/statistics">${statistics}</a>
</header>
<h1>${addExhibition}</h1>
<h3>${errorMessage}</h3>
<div class="form">


    <form method="post" action="" accept-charset="utf-8">

        <label>${topic}
            <input type="text" required name="topic">
        </label><br>
        <label>${startDate}
            <input type="date" required min="${now}" name="startDate">
        </label><br>
        <label>${endDate}
            <input type="date" required min="${now}" name="endDate">
        </label><br>
        <label>${startTime}
            <input type="time" required name="startTime">
        </label><br>
        <label>${endTime}
            <input type="time" required name="endTime">
        </label><br>
        <label>${rooms}
            <input type="text" required name="rooms">
        </label><br>
        <label>${price}
            <input type="text" required name="price">
        </label><br>
        <input class="button" type="submit" value="${addExhibition}">

    </form>
</div>
</body>
</html>
