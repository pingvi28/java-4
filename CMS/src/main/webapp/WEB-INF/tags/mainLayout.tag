<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>
<%@attribute name="css" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta charset="UTF-8" />
    <title>${title}</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@600&display=swap" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/common.css" />" rel="stylesheet">
    <link href="<c:url value="/css/${css}" />" rel="stylesheet">
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/navConstructor.jsp"/>
    <jsp:doBody/>
</div>
</body>
</html>
