<!doctype html>
<html lang="ru">
<#import "/spring.ftl" as spring/>
<#import "../patterns/header.ftlh" as hf />
<#import "../patterns/bootstrap.ftl" as boot />

<head>
    <@boot.importBootstrap />
    <@hf.headForHeader />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message "sign_up_page.registration.title"/></title>
    <link rel="stylesheet" href="<@spring.url '/assets/css/login_form.css' />">
</head>
<body>
<@hf.header isAuthenticated=false />
<@spring.bind "userForm"/>
<div class="login-dark">
    <form method="post">
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>

        <#if error_message??>
            <h4 >${error_message}</h4>
        </#if>

        <br>
        <label for="login"> <@spring.message "login.label"/> </label>
        <div class="required w-100"><@spring.message 'login.required'/></div>
        <br>
        <@spring.formInput "userForm.login" "class='w-100' pattern='[a-zA-Z0-9Р-пр-џ\\._-]{3,}'"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <br>
        <label for="email"> <@spring.message "email.label"/> </label>
        <@spring.formInput "userForm.email" "class='w-100' pattern='^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?)|([0-9Р-пр-џ]{1}[-0-9Р-џ\\.]{0,30}[0-9Р-пр-џ]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$'"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <br>
        <label for="password" id="password"> <@spring.message "password.label"/> </label>
        <div class="required w-100"><@spring.message 'password.required'/></div>
        <br>
        <@spring.formPasswordInput "userForm.password" "class='w-100' pattern='*[0-9a-zA-ZР-пр-џ!@#$%^&*].{6,}'"/>
        <@spring.showErrors "error"/>
        <br>
        <label for="passwordRepeat" id="passwordRepeat" > <@spring.message "repPassword.label"/> </label>
        <br>
        <@spring.formPasswordInput "userForm.passwordRepeat" "class='w-100'"/>
        <@spring.showErrors "error"/>
        <br>
        <br>
        <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
        <button class="btn btn-primary d-block w-100" type="submit"><@spring.message "sign_up_page.registration.title"/></button>
    </form>
</div>
<#if repeatableFields??>
    <p>${repeatableFields}</p>
</#if>

<script src="<@spring.url '/js/pasSigUpCorrect.js'/> " type="text/javascript" ></script>

</body>
</html>