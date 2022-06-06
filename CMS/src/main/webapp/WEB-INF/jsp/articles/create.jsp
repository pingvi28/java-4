<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<t:mainTag title="Создание" css="article.css">

    <form:form class="form-horizontal" method="POST" modelAttribute="article">

        <t:inputTag label="Название статьи" path="name" required="true"/>

        <t:textareaTag label="Контент" path="content" id="content" required="true"/>

        <c:if test="${not empty message}">
            <div class="global-message">${message}</div>
        </c:if>

        <p class="forAdmin"><input type="checkbox" name="checkbox">Для админов </p>
        <input type="submit" class="btn btn-success save" value="Сохранить">
    </form:form>

    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
    <script>
        ClassicEditor
            .create(document.querySelector('#content'))
            .catch(error => {
                console.error(error);
            });
    </script>
</t:mainTag>
