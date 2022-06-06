<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<t:mainTag title="Статьи" css="article.css">

    <table class="table">
        <tbody>
        <c:forEach items="${articles}" var="article">
            <tr>
                <td class="for">
                    <c:if test="${article.getRole() == 'ADMIN'}">
                        Для админов
                    </c:if>
                    <c:if test="${article.getRole() == 'USER'}">
                        Для пользователей
                    </c:if>
                </td>
                <td>
                    <div class="accordionn">
                        <a href="${article.getSlug()}">${article.getName()}</a>
                    </div>
                    <div class="myPanel ">
                            ${article.getContent() }
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <script>
        var acc = document.getElementsByClassName("accordionn");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function() {
                this.classList.toggle("active");
                var panel = this.nextElementSibling;
                if (panel.style.maxHeight){
                    panel.style.maxHeight = null;
                } else {
                    panel.style.maxHeight = panel.scrollHeight + "px";
                }
            });
        }
    </script>
</t:mainTag>

