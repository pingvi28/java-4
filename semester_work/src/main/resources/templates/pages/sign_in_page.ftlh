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
  <title>Sign In</title>
  <link rel="stylesheet" href="<@spring.url '/assets/css/login_form.css' />">
</head>
<body>
<@hf.header false />
<#if loginForm??>
  <@spring.bind "loginForm"/>
</#if>

<section class="login-dark">
  <form method="post">
    <#if error_message??>
      <h4 >${error_message}</h4>
    </#if>

    <br>
    <label for="email"> <@spring.message "email.label"/> </label>
    <br>
    <@spring.formInput "loginForm.email" "class='w-100' pattern='^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?)|([0-9А-Яа-я]{1}[-0-9А-я\\.]{0,30}[0-9А-Яа-я]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$'"/>
    <@spring.showErrors "<br>" "error"/>
    <br>
    <br>
    <label for="password" id="password"> <@spring.message "password.label"/> </label>
    <br>
    <@spring.formPasswordInput "loginForm.password" "class='w-100'"/>
    <@spring.showErrors "error"/>
    <br>
    <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
    <button class="btn btn-primary d-block w-100" type="submit"><@spring.message "sign_in_page.registration.title"/></button>
    <br>
    <br>

    <div class="vkbut">
      <button>
        <a class="btn btn-light action-button vkbut" role="button"  href="https://oauth.vk.com/authorize?client_id=7984087&display=page&redirect_uri=http://localhost:8084/signIn/token&scope=offline,email&response_type=code&v=5.131">
          Войти через vk</a>
      </button>
    </div>
  </form>

  <input type="hidden" id="_csrf" value="${_csrf.token}" name="${_csrf.headerName}">
</section>
</body>
</html>