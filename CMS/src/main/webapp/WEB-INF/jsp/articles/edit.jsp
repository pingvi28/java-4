<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<t:mainLayout title="Редактирование" css="article.css">

    <form:form class="form-horizontal" method="POST" modelAttribute="article">

        <t:input label="Name" path="name" required="true">
            ${article.getName()}
        </t:input>

        <br>
        <br>
        <br>

        <t:textarea label="Сontent" path="content" id="content" required="true">
            ${article.getContent()}
        </t:textarea>

        <br>
        <br>

        <c:if test="${not empty message}">
            <div class="global-message">${message}</div>
        </c:if>

        <br>
        <br>

        <input type="submit" class="btn" value="Сохранить статью">
    </form:form>

    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
    <script>
        ClassicEditor
            .create(document.querySelector('#content'))
            .catch(error => {
                console.error(error);
            });
    </script>

</t:mainLayout>

