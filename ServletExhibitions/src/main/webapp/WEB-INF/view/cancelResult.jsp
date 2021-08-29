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
<%
        request.setAttribute("home", View.view.getBundleText(ITextsPaths.HOME));

        request.setAttribute("login", View.view.getBundleText(ITextsPaths.LOGIN));
        request.setAttribute("registration", View.view.getBundleText(ITextsPaths.REGISTER));
        request.setAttribute("addExhibition", View.view.getBundleText(ITextsPaths.ADD_EXHIBITION_HREF));
        request.setAttribute("statistics", View.view.getBundleText(ITextsPaths.STATISTICS_HREF));

        request.setAttribute("cancel", View.view.getBundleText(ITextsPaths.CANCEL_EXHIBITION));
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
        <a href="${pageContext.request.contextPath}/statistics">${statistics}</a>
        <a href="${pageContext.request.contextPath}/addExhibition">${addExhibition}</a>
</header>
<h1>${cancel}</h1>
</body>
</html>
