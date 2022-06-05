<#import "/spring.ftl" as spring/>
<#import "../patterns/header.ftlh" as hf />
<#import "../patterns/bootstrap.ftl" as boot />

<#macro error title discription="" >
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
    <@boot.importBootstrap />
    <@hf.headForHeader />
    <link rel="stylesheet" href="<@spring.url '/assets/fonts/ionicons.min.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/main_highlight.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/error.css' />">
</head>
<body>
<@hf.header false />
<div class="content">
    <h1>${discription}</h1>
</div>
</body>
</html>
</#macro>