<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.model.services.ExhibitionService" %>
<%@ page import="ua.project.model.entity.Role" %>
<%@ page import="java.time.LocalDate" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
    <title>Welcome</title>
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

    request.setAttribute("filterByDate", View.view.getBundleText(ITextsPaths.FILTER_BY_DATE));
    request.setAttribute("submit", View.view.getBundleText(ITextsPaths.SUBMIT));
    request.setAttribute("now", LocalDate.now());
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

<form action="${pageContext.request.contextPath}/filter" method="post">
    <label>${filterByDate}</label>
    <input type="date" name="filterDate" min="${now}" value="" required>
    <input type="submit" name="filterSubmit" min="${now}" value="${submit}">
</form>

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
        <c:when test="${filteredList.size() > 0}">
            <c:forEach var="item" items="${filteredList}">
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

</body>
</html>
