<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>

<!DOCTYPE html>

<html>
<head>

    <meta charset="UTF-8">

    <title>${local.title}</title>
</head>
<body>
<div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
    <p style="font-style:italic; padding: 0 40px"> сообщении из базы данных  </p>
    <a href="/my_LocaleResolver_war/EN/login2">Login (English)</a>
    <a href="/my_LocaleResolver_war/RU/login2">Login (Russia)</a>
</div>

<form method="post" action="">
    <table>
        <tr>
            <td>
                <strong>${local.userName}</strong>
            </td>
            <td><input name="userName" /></td>
        </tr>
        <tr>
            <td>
                <strong>${local.password}</strong>
            </td>
            <td><input name="password" /></td>
        </tr>
        <tr>
            <td colspan="2">
                ${local.submit}
                <input type="submit" value=${local.input} /> />
            </td>
        </tr>
    </table>
</form>
</body>
</html>