<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@page contentType="text/html; charset=utf-8"%>

<nav>
    <div class="container-fluid topnav">
        <ul >
            <div class="topnav-left">
                <li><a href="${spring:mvcUrl('AC#index').build()}">Статьи</a></li>
            </div>
            <div class="topnav-right">
                <security:authorize access="isAnonymous()">
                    <li><a href="${spring:mvcUrl('RC#registration').build()}">Регистрация</a></li>
                    <li><a href="<spring:url value="/login"/>">Вход в аккаунт</a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                        <security:authorize access="hasAuthority('ADMIN')">
                            <li><a href="${spring:mvcUrl('AC#createIndex').build()}">Создать новую статью</a></li>
                        </security:authorize>

                    <li><a href="<spring:url value="/logout" />">Выход</a></li>
                </security:authorize>
            </div>
        </ul>
    </div>
</nav>
