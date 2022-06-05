<#macro cardhead>
  <link rel="stylesheet" href="<@spring.url '/assets/css/card.css' />">
</#macro>

<#macro card title href description="" >

  <div class="card border-secondary mb-3" style="max-width: 18rem;">
    <div class="card-header">${title}</div>
    <div class="card-body text-secondary">
      <h5 class="card-title"><a class="btn btn-primary" role="button" href="${href}"> Подробнее </a>
      </h5>
      <p class="card-text">${description}</p>
    </div>
  </div>
</#macro>