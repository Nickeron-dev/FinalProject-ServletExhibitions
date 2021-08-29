<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.model.services.ExhibitionService" %>
<%@ page import="ua.project.model.entity.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html> <!--xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core"-->
<head>
    <title>Welcome</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" accept-charset="character_set"/>
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

        table {
            margin-top: 0.5em;
            margin-left: auto;
            margin-right: auto;
        }

        .cancel {
            background: red;
        }

        .buy {
            width: 100%;
            background: green;
        }

        .plan {
            background: yellow;
        }

        .pages {
            overflow: visible;
            width: auto;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("welcome", View.view.getBundleText(ITextsPaths.WELCOME));
    request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
    request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));
    request.setAttribute("role", request.getSession().getAttribute("role"));
    ExhibitionService service = new ExhibitionService();
    request.setAttribute("allExhibitions", service.findAll());
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
    request.setAttribute("noElementsFound", View.view.getBundleText(ITextsPaths.ELEMENTS_NOT_FOUND));
    request.setAttribute("statistics", View.view.getBundleText(ITextsPaths.STATISTICS_HREF));
    request.setAttribute("addExhibition", View.view.getBundleText(ITextsPaths.ADD_EXHIBITION_HREF));
    try {
        if (request.getSession().getAttribute("role").equals(Role.ADMIN)) {
            request.setAttribute("isAdmin", "true");
        }
    } catch (NullPointerException ignored) {

    }

    request.setAttribute("pagesNumber", service.pagesAvailable());
%>
<header>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="submit" name="ukr" value="UKR">
        <input type="submit" name="eng" value="ENG">
    </form>
    <a href="${pageContext.request.contextPath}/">${home}</a>
    <a href="${pageContext.request.contextPath}/login">${login}</a>
    <a href="${pageContext.request.contextPath}/registration">${registration}</a>
    <c:if test="${isAdmin == true}">
        <a href="${pageContext.request.contextPath}/statistics">${statistics}</a>
        <a href="${pageContext.request.contextPath}/addExhibition">${addExhibition}</a>
    </c:if>


</header>
<h1>${welcome}</h1>

<table border="2">
    <tr>
        <th>${topic}</th>
        <th>${startDate}</th>
        <th>${endDate}</th>
        <th>${startTime}</th>
        <th>${endTime}</th>
        <th>${rooms}</th>
        <th>${price}</th>
        <th>${state}</th>
        <th>${buyTicket}</th>
        <c:if test="${isAdmin == true}">
            <th>${cancel}</th>
        </c:if>
        <c:if test="${isAdmin == true}">
            <th>${plan}</th>
        </c:if>

    </tr>
    <c:choose>
        <c:when test="${allExhibitions.size() > 0}">
            <c:forEach var="item" items="${page}">
                <tr>
                    <td>${item.topic}</td>
                    <td>${item.startDate}</td>
                    <td>${item.endDate}</td>
                    <td>${item.startTimeEveryDay}</td>
                    <td>${item.endTimeEveryDay}</td>
                    <td>${item.rooms}</td>
                    <td>${item.price}</td>
                    <td>${item.state}</td>
                    <td><form action="${pageContext.request.contextPath}/buy" method="post">
                        <input class="buy" type="submit" name="${item.id}" value="${buy}">
                    </form></td>
                    <c:if test="${isAdmin == true}">
                        <td><form action="${pageContext.request.contextPath}/cancel" method="post">
                            <input class="cancel" type="submit" name="${item.id}" value="${cancel}">
                        </form></td>
                    </c:if>
                    <c:if test="${isAdmin == true}">
                        <td><form action="${pageContext.request.contextPath}/plan" method="post">
                            <input class="plan" type="submit" name="${item.id}" value="${plan}">
                        </form></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>${noElementsFound}</h2>
        </c:otherwise>
    </c:choose>
</table>

<form action="${pageContext.request.contextPath}/" method="post">
    <c:forEach var="i" begin="1" end="${pagesNumber}" step="1">
        <input class="pages" type="submit" name="pageId" value="${i}">
    </c:forEach>
</form>
</body>
</html>
