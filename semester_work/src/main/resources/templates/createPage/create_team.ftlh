<#import "../patterns/bootstrap.ftl" as boot />
<#import "/spring.ftl" as spring />
<#import "../patterns/header.ftlh" as hf />
<#import "../patterns/form.ftlh" as form />

<!doctype html>
<html lang="en">

<head>
    <@boot.importBootstrap />
    <@hf.headForHeader />
    <@form.headForInputForm />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'resume' /></title>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <section class="contact-clean content">
        <form method="post">
            <h3 class="text-center"><@spring.message 'create_team' /></h3>
            <br>
            <br>
            <@form.inputForm name="name" type="text"/>
            <div class="mb-3">
                <p><@spring.message "create_resume_contact"/> </p>
                <p>${creator.email}</p>
                <input type="hidden" value="${creator.login}" name="creatorLogin">
                <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
                <button class="btn btn-primary" type="submit"><@spring.message 'send' /></button>
            </div>
        </form>
    </section>
</div>
</body>
</html>