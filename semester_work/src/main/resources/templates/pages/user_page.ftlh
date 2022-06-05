<!doctype html>
<html lang="`en`">
<#import "../patterns/bootstrap.ftl" as boot />
<#import "../patterns/header.ftlh" as fh />
<#import "/spring.ftl" as spring/>

<head>
    <@boot.importBootstrap />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<@spring.url '/assets/css/main_highlight.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/userStat.css' />">
    <title>${user.login}</title>
</head>
<body>
<div class="wrapper">
    <@fh.header false/>
    <ul class="list-group contentSt">
        <li class="list-group-item"> Логин пользователя :  ${user.login}</li>
        <li class="list-group-item"><a href="<@spring.url '/users/${user.login}/teams/' />"> Список команд </a></li>
    </ul>
</div>

<div class="contentSt">
    <p><@spring.message 'user.statistic' /></p>


    <li class="list-group-item">${user.adCount}</li>
    <p><@spring.message 'user.statistic.adCount' /></p>
    <li class="list-group-item">${user.resumeCount}</li>
    <p><@spring.message 'user.statistic.resumeCount' /></p>
    <li class="list-group-item">${user.teamCount}</li>
    <p><@spring.message 'user.statistic.teamCount' /></p>
</div>
</body>
</html>