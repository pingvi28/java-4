<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/15/2022
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web-framework</title>
</head>
<body>
 <jsp:useBean id="booksHashMap" scope="request"
              class="ru.kpfu.itis.kashapova.framework.database.BooksHashMap"/>
 <%
     booksHashMap.getBook();
 %>
</body>
</html>
