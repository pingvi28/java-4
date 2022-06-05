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
    <title><@spring.message 'ad' /></title>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <section class="contact-clean content">
        <form method="post">
            <h3 class="text-center"><@spring.message 'create_ad'/></h3>
            <br>
            <br>
            <label for="title"> <@spring.message "create_ad_title"/> </label>
            <@form.inputForm name="header" type="text" id=""/>
            <label for="description" id="contentLab"> <@spring.message "create_ad_description"/> </label>
            <@form.textare name="description" type="text" />
            <label for="contact"> <@spring.message "create_ad_contact"/> </label>
            <@form.inputForm name="contact" type="text" id=""/>
            <label for="price"> <@spring.message "create_ad_price"/> </label>
            <@form.inputForm name="price" type="value" id=""/>
            <div class="mb-3">
                <input type="hidden" value="${user.login}" name="creatorLogin">
                <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
                <button class="btn btn-primary" type="submit"><@spring.message 'send' /></button>
            </div>
         </form>
    </section>
</div>

<script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
<script>
    ClassicEditor
        .create(document.querySelector('#contentLab'))
        .catch(error => {
            console.error(error);
        });
</script>

</body>
</html>