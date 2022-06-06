<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<t:mainTag title="${article.getTitle()}" css="article.css">
    <div class="articleName">

    </div>
    <div class="articleContent">
        <h2>${article.getName()}</h2>
        <br>
        <h3> Дата создания: ${article.getCreatingTime()}</h3>

        <h4>${article.getContent()}</h4>
    </div>

    <sec:authorize access="isAuthenticated()">
        <sec:authorize access="hasAuthority('ADMIN')">
            <a href="<spring:url value="/edit/${article.getSlug()}"/>" class="btn btn-success create">Редактировать статью</a>
        </sec:authorize>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <sec:authorize access="hasAuthority('ADMIN')">
            <a href="<spring:url value="/delete/${article.getSlug()}"/>" class="btn btn-success delete">Удалить статью</a>
        </sec:authorize>
    </sec:authorize>
</t:mainTag>

