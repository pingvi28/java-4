<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<t:mainLayout title="Registration" css="register.css">
<div class = "form">
    <form:form class="form-horizontal" method="POST" modelAttribute="user">
        <t:input label="E-mail" path="email" required="true"/>
        <t:input label="Имя" path="name" required="true"/>
        <t:password label="Пароль" path="password" required="true"/>
        <a href="#" class="password-control1"></a>
        <t:password label="Повторите пароль" path="passwordRepeat" required="true"/>

        <button type="submit" class="btn btn-success">Зарегистрироваться</button>
    </form:form>
</div>
</t:mainLayout>

