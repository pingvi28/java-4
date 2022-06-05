<!doctype html>
<html lang="en">
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
    <link rel="stylesheet" href="<@spring.url '/assets/css/login_form.css' />">
    <title><@spring.message "sign_up_page.registration.title"/></title>
</head>
<body>
<@hf.header isAuthenticated=true />
<@spring.bind "userForm"/>
<section class="login-dark">
    <form method="post">
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
        <br>
        <label for="login"> <@spring.message "login.label"/> </label>
        <div class="required w-100"><@spring.message 'login.required'/></div>
        <br>
        <@spring.formInput "userForm.login" "class='w-100' pattern='[a-zA-Z0-9Р-пр-џ\\._-]{3,}'"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <label> <@spring.message "email.label"/> </label>
        <br>
        <@spring.formInput "userForm.email"  "class='w-100'"/>
        <@spring.showErrors "error"/>
        <br>
        <label for="password"> <@spring.message "password.label"/> </label>
        <br>
        <@spring.formPasswordInput "userForm.password" "class='w-100'"/>
        <@spring.showErrors "error"/>
        <br>
        <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
        <button class="btn btn-primary d-block w-100" type="submit">Update</button>
    </form>
</section>
</body>
</html>