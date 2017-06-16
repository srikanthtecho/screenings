<#import "layouts/main.ftl" as mainLayout>
<#import "/spring.ftl" as spring />

<script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
  jQuery.noConflict();
     $(".answer").focusout(function(event){

     $.ajax({
         url: '/interviews/${model.interview.id}/answer',
         type: 'PUT',
         data: JSON.stringify({"questionId": event.target.id, "answer": event.target.value}),
         contentType: 'application/json',
         success: function(result) {

         },
         error: function(jqXHR,error, errorThrown) {
               if(jqXHR.status&&jqXHR.status==500){
                   alert("Something went wrong");
               }
          }
     });
      event.isPropagationStopped();
    });

  });
</script>

<@mainLayout.application "Assessments">

Interview started for the candidate <em>${model.interview.candidateName}</em>
for  the position <em>${position.positionName}</em>

<div class="jumbotron">

 <#if model.questions?has_content>
  <ul>
    <#list model.questions as question>
      <div class = "row">
      <div>
      ${question?counter}. ${question.question}
      </div>
      <div class = "col-sm-5">
             <textarea  class="answer" id = ${question.id} rows = "3" cols="40" >
             <#if question.candidateAnswer.actualAnswer?has_content>
                ${question.candidateAnswer.actualAnswer}
             </#if>
             </textarea>
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
  <#else>
  No questions attached to this position.
</#if>
</div>
</@mainLayout.application>
