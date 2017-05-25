<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

<div class="jumbotron">
    <h1>Positions</h1>
    <p class="lead">From here you can create a new position.  Once the position is created you can start adding questions to it.</p>
    <p>

        <html>
        ...
        <form action="/positions" method="POST" id="form">
            <@spring.bind "position" />
            <table>
                <tr>
                    <td>Name:</td>
                    <td>  <@spring.formInput "position.positionName" /> </td>
                </tr>

                <tr>
                    <td>Company Name:</td>
                    <td>  <@spring.formInput "position.companyName" /> </td>
                </tr>

                <tr>
                    <td>Recruiter:</td>
                    <td>  <@spring.formInput "position.recruiterId" /> </td>
                </tr>


                <tr>
                    <td colspan="2">
                        <a class="btn btn-lg btn-success" onclick="document.getElementById('form').submit();" href="#" role="button">Create New Position</a>
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

<h2>List of Positions</h2>
<div class="row marketing">
<#list positions as position>
<div class="col-lg-6">
<span>${position.positionName}</span>
<span>${position.companyName}</span>
<span><a href="/positions/${position.id}">Detail</a></span>
</div>
</#list>
</div>
</@mainLayout.application>
