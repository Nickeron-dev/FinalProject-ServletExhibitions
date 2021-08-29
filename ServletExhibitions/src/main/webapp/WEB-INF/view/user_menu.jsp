<%@ page import="ua.project.model.entity.Role" %>
<%@ page import="ua.project.view.ITextsPaths" %>
<%@ page import="ua.project.view.View" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));
    request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
    request.setAttribute("logout", View.view.getBundleText(ITextsPaths.LOGOUT_HREF));
    request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));

    request.setAttribute("statistics", View.view.getBundleText(ITextsPaths.STATISTICS_HREF));
    request.setAttribute("addExhibition", View.view.getBundleText(ITextsPaths.ADD_EXHIBITION_HREF));
    request.setAttribute("helloUser", View.view.getBundleText(ITextsPaths.GREETING_USER));
%>
<head>
    <title>USER</title>
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

        .logout {
            float: none;
            text-align: center;
            background-color: yellow;
        }

        .logout:hover {
            background-color: darkkhaki;
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
    <c:if test="${isAdmin == true}">
        <a href="${pageContext.request.contextPath}/statistics">${statistics}</a>
        <a href="${pageContext.request.contextPath}/addExhibition">${addExhibition}</a>
    </c:if>


</header>
<h1>${helloUser}</h1>
<a class="logout" href="<c:url value="/logout"/>">${logout}</a>
</body>
</html>
