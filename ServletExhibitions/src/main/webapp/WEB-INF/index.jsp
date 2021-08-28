<%@ page import="ua.project.view.View" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.model.services.ExhibitionService" %>
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
<%--        <c:if test="${isAdmin == true}">--%>
<%--            <th>${cancel}</th>--%>
<%--        </c:if>--%>
<%--        <c:if test="${isAdmin == true}">--%>
<%--            <th>${plan}</th>--%>
<%--        </c:if>--%>

    </tr>
    <c:choose>
        <c:when test="${allExhibitions.size() > 0}">
            <c:forEach var="item" items="${allExhibitions}">
                <tr>
                    <td>${item.topic}</td>
                    <td>${item.startDate}</td>
                    <td>${item.endDate}</td>
                    <td>${item.startTimeEveryDay}</td>
                    <td>${item.endTimeEveryDay}</td>
                    <td>${item.rooms}</td>
                    <td>${item.price}</td>
                    <td>${item.state}</td>
                    <td><form action="/buy" method="post">
                        <input class="buy" type="submit" name="${item.id}" value="buy">
                    </form></td>
<%--                    <c:if test="${isAdmin == true}">--%>
<%--                        <td><form:form action="/cancel" method="post">--%>
<%--                            <input class="cancel" type="submit" name="${item.id}" value="${cancel}">--%>
<%--                        </form:form></td>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${isAdmin == true}">--%>
<%--                        <td><form:form action="/plan" method="post">--%>
<%--                            <input class="plan" type="submit" name="${item.id}" value="${plan}">--%>
<%--                        </form:form></td>--%>
<%--                    </c:if>--%>
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
