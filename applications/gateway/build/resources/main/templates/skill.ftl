<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

<div class="jumbotron">
    <h1>Skills</h1>
    <p class="lead">A list of all the skills we want to hire for.  Add skills and you will add questons for each skill.</p>
    <p>

        <html>

        <form action="/skills" method="POST" id="form">
            <@spring.bind "skill" />
            <table>
                <tr>
                    <td>Name:</td>
                    <td>  <@spring.formInput "skill.name" /> </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <a class="btn btn-lg btn-success" onclick="document.getElementById('form').submit();" href="#" role="button">Create New Skill</a>
                    </td>
                </tr>
            </table>
        </form>
            <#--Name:-->
            <#--<@spring.bind "command.name" />-->
            <#--<input type="text"-->
                   <#--name="${spring.status.expression}"-->
                   <#--value="${spring.status.value?default("")}" /><br>-->
            <#--<#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>-->
            <br>


    </p>

<#--<p>${today}</p>-->
</div>
<h2>All Skills.</h2>
<div class="row marketing">
<#list skills as skill>
<div class="col-lg-6">
<span>${skill.name}</span>
<span><a href="/skills/${skill.id}">Detail</a></span>
</div>
</#list>
</div>
</@mainLayout.application>
