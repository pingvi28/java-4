<!doctype html>
<html lang="en">
<#import "/spring.ftl" as spring/>
<#import "../patterns/header.ftlh" as hf />
<#import "../patterns/card.ftlh" as card />
<#import "../patterns/bootstrap.ftl" as boot />

<head>
    <@boot.importBootstrap />
    <link rel="stylesheet" href="<@spring.url '/assets/css/main_highlight.css' />">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Teams</title>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <div class="card-group" style="margin: 30px">
        <#list teams as team>
            <@card.card title=team.name href="/teams/own"  />
        </#list>
    </div>
</div>
</body>
</html>