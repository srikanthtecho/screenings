<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

<div class="jumbotron">
    <h1>${position.positionName}</h1>
    <p class="lead">Check all the skills that apply to this position.  </p>
    <h2>All Skills</h2>
    <p>
    <form action="/positions/link" method="POST" id="form">
        <#--<@spring.bind "position" />-->
        <@spring.formHiddenInput "position.id"/>


    <div class="row marketing">
        <#list skills as skill>
            <div class="col-lg-6">
                <input type="checkbox" name="skills" ${position.skills?seq_contains(skill.id)?string("checked=true", "")} value="${skill.id}"> <span>${skill.name}</span>
            </div>
        </#list>
    </div>

    <div class="row marketing">
            <a class="btn btn-lg btn-success" onclick="document.getElementById('form').submit();" href="#" role="button">Assiocate Skills</a>
    </div>
</form>
    </p>

<#--<p>${today}</p>-->
</div>


</@mainLayout.application>
