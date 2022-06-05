<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>

    <link href="<c:url value="/css/common.css" />" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/error.css" />" rel="stylesheet">
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/jsp/navConstructor.jsp"/>
    <jsp:doBody/>
</div>
</body>
</html>
