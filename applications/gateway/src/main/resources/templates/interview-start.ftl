<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

Interview started for the candidate <em>${model.interview.candidateName}</em> for  the position <em>${position.positionName}</em>

<div class="jumbotron">
  <ul>
    <#list model.questions as question>
      <div class = "row">
      <div>
      ${question?counter}. ${question.question}
      </div>
      <div class = "col-sm-5">
         <textarea rows = "3" cols="40" > </textarea>
      </div>
      <div class = "col-sm-5">
        <small> ${question.correctAnswer} </small>
       </div>
       <div class="col-sm-2">
         <select class="form-control" id="sel1">
            <option>Rate this</option>
           <option>Okay</option>
           <option>Good</option>
           <option>Excellent</option>
         </select>
       </div>
      </div>

    </#list>
  </ul>
</div>
</@mainLayout.application>
