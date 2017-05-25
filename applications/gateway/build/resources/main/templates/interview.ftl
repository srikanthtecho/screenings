<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

<div class="jumbotron">
    <h1>Skills</h1>
    <p class="lead">Setup and manage interviews. </p>
    <p>

        <html>

        <form action="/interviews" method="POST" id="form">
            <@spring.bind "interview" />
            <table>
                <tr>
                    <td>Candidate Name:</td>
                    <td>  <@spring.formInput "interview.candidateName" /> </td>
                </tr>
                <tr>
                    <td>Interviewer Name:</td>
                    <td>  <@spring.formInput "interview.interviewer" /> </td>
                </tr>
                <tr>
                    <td>Interview Type (L1 or L2)</td>
                    <td>  <@spring.formInput "interview.type" /> </td>
                </tr>
                <tr>
                    <td>Position</td>
                    <td>  <@spring.formInput "interview.type" /> </td>
                </tr>
                <tr>
                    <td>Interview Date</td>
                    <td>  <@spring.formInput "interview.type" /> </td>
                </tr>
                <tr>
                    <td>General Notes</td>
                    <td>  <@spring.formInput "interview.notes" /> </td>
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
<h2>All Interviews.</h2>
<div class="row marketing">
<#list interviews as intview>
<div class="col-lg-6">
<span>${intview.dateAsText}</span> <span>${intview.positionText}</span> <span>${intview.candidateName}</span> <span>${intview.interviewer}</span> <span>${intview.status}</span> <span>${intview.type}</span> <span><a href="/intervews/${intview.id}">Detail</a></span> <span><a href="/intervews/${intview.id}/start">Start Interview</a></span>
</div>
</#list>
</div>
</@mainLayout.application>
