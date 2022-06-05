<#macro headForInputForm>
    <link rel="stylesheet" href="<@spring.url '/assets/css/Contact-Form-Clean.css' />">
</#macro>

<#macro inputForm type name id>
<div class="mb-3">
    <input class="form-control" type="${type}" name="${name}" id="${id}">
</div>
</#macro>

<#macro textare type name>
    <div class="input-group">
        <textarea class="form-control" aria-label="With textarea"></textarea>
    </div>
</#macro>
