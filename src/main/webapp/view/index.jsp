<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>

<html>
<head>
    <title>News Portal</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

</head>
<body>

<!-- Wrapper -->
<div id="wrapper">

    <!-- Header -->
    <header id="header">
        <h1></h1>
    </header>
    <!-- Main -->
    <div id="main">
        <c:if test="${billView == null}">
            <li><a href="?lang=en" style="color:#800000"><spring:message code="label.english"/></a></li>
            <li><a href="?lang=ru" style="color:#800000"><spring:message code="label.russian"/></a></li>
            <p><spring:message code="label.guide"/></p>
        </c:if>

        <c:forEach items="${billView}" var="line">
            <c:out value="${line}"/>
            <br>
        </c:forEach>

    </div>

</div>

</body>
</html>