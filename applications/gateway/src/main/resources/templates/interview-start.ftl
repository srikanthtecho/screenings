<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<@mainLayout.application "Assessments">

Interview started for the candidate ${model.interview.candidateName} for  the position ${position.positionName}

<div class="jumbotron">
  <ul>
    <#list model.questions as question>
      <div class = "row">
      <div class = "col-md-12">
      ${question?counter}. ${question.question}
      </div>
      <div class = "col-md-5">
         <textarea rows="4" cols="50"> </textarea>
      </div>
      <div class = "col-md-5">
        <small> ${question.correctAnswer} </small>
       </div>
       <div class="col-md-2">
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
