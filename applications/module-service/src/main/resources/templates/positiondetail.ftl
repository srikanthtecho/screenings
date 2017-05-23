<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

<div class="jumbotron">
    <h1>${position.positionName}</h1>
    <p class="lead">A list of all the skills we want to hire for</p>
    <h2>All Skills</h2>
    <p>
    <div class="row marketing">
        <#list skills as skill>
            <div class="col-lg-6">
                <input type="checkbox" name="${skill.id}" ${position.skills?seq_contains(skill.id)?string("checked=true", "")} <span>${skill.name}</span>
            </div>
        </#list>
    </div>



    </p>

<#--<p>${today}</p>-->
</div>


</@mainLayout.application>
