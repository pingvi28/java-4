<!doctype html>
<#import "/spring.ftl" as spring/>
<#import "../patterns/header.ftlh" as hf />
<#import "../patterns/card.ftlh" as card />
<#import "../patterns/bootstrap.ftl" as boot />
<html lang="en">

<head>
    <@boot.importBootstrap />
    <@hf.headForHeader />
    <link rel="stylesheet" href="<@spring.url '/assets/css/main_highlight.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/list_ad.css' />">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'ads' /></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <@hf.header false />
    <div class="card-group content" style="margin: 30px">
        <#list ads as ad>
            <div class="card-body" style="background-color: #f0f0f0; border: 4px double #444">
                <h4 class="card-title">${ad.header}</h4>
                <button class="btn btn-primary ajax" value="${ad.id}"> Подробнее</button>
            </div>
        </#list>
    </div>


    <div id="myModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2 id="header"></h2>
                <span class="close">&times;</span>
            </div>
            <div class="modal-body">
                <p id="description"></p>
                <p id="price"></p>
            </div>
            <div class="modal-footer">
                <h3 id="contact"></h3>
            </div>
        </div>
    </div>

    <script defer>
        let modal = document.getElementById("myModal");
        let btns = document.getElementsByClassName("ajax");
        let span = document.getElementsByClassName("close")[0];

        for (let btn of btns) {
            let url_id = "/ads/" + btn.value
            btn.onclick = async function () {
                let response = await fetch(url_id);
                let data = await response.json()
                $("#header").html(data.header)
                $("#description").html(data.description)
                $("#price").html(data.price)
                $("#contact").html(data.contact)
                modal.style.display = "block"
            }
        }

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }
    </script>
</div>
</body>
</html>
