<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>

<!DOCTYPE html>

<html>
<head>

    <meta charset="UTF-8">

    <title><spring:message code="label.title" /></title>
</head>
<body>

<div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
    <p style="font-style:italic; padding: 0 40px"> на основе доменного имени  </p>
    <a href="/my_LocaleResolver_war/en/login">Login (English)</a>
    <a href="/my_LocaleResolver_war/fr/login">Login (French)</a>
    <a href="/my_LocaleResolver_war/ru/login">Login (Russia)</a>
</div>

<form method="post" action="">
    <table>
        <tr>
            <td>
                <strong>
                    <spring:message  code="label.userName" />
                </strong>
            </td>
            <td><input name="userName" /></td>
        </tr>
        <tr>
            <td>
                <strong>
                    <spring:message  code="label.password" />
                </strong>
            </td>
            <td><input name="password" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <spring:message code="label.submit" var="labelSubmit"></spring:message>
                <input type="submit" value=<spring:message  code="label.input" /> />
            </td>
        </tr>
    </table>
</form>
</body>
</html>