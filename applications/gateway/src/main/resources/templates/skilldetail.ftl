<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Skill Detail">

<div class="jumbotron">
    <h1>${skill.name}</h1>
    <p class="lead">All the questions that are assiocated with a skill</p>
    <p>

        <form action="/skills/questions" method="POST" id="form">
            <@spring.bind "question" />
            <@spring.formHiddenInput "question.skillId"/>
            <table>
                <tr>
                    <td>Question:</td>
                    <td><@spring.formTextarea "question.question" /> </td>
                </tr>
                <tr>
                    <td>Correct Answer:</td>
                    <td><@spring.formTextarea "question.correctAnswer" /> </td>
                </tr>
                <tr>
                    <td>Level (L1 or L2):</td>
                    <td><@spring.formInput "question.level"/> </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <a class="btn btn-lg btn-success" onclick="document.getElementById('form').submit();" href="#" role="button">Add Question</a>
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

<h3>Questions</h3>
<div class="row marketing">
    <#list skill.questions as question>
        <div class="col-lg-6">
            <span>${question.level}</span>
            <span>${question.question}</span>
            <span>${question.correctAnswer}</span>
        </div>
    </#list>
</div>
</@mainLayout.application>

