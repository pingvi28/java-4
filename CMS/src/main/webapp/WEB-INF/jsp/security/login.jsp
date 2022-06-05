<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Log in" css="login.css">
    <form:form class="form-horizontal" method="POST" modelAttribute="loginForm">
        <t:input label="E-mail" path="email" required="true"/>
        <t:password label="Пароль" path="password" required="true"/>
        <button type="submit" class="btn btn-success">Войти в аккаунт</button>
    </form:form>
</t:mainLayout>

