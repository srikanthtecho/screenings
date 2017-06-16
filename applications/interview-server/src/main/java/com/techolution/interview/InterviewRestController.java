package com.techolution.interview;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by tdelesio on 5/24/17.
 */
@RestController
@RequestMapping(value = "/json/interviews")
public class InterviewRestController {

    private InterviewService interviewService;

    public InterviewRestController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }


    @PostMapping
    public Interview createInterview(@RequestBody Interview interview) {

        return interviewService.createInterview(interview);
    }

    @GetMapping
    public List<Interview> getAllInterviews() {

        return interviewService.getAllInterviews();
    }

    @GetMapping("/{id}")
    public Interview interviewDetails(@PathVariable String id) {

        return interviewService.getInterviewById(id);
    }

    @GetMapping("/{id}/start")
    public Map<String, Object> startInterview(@PathVariable String id) {

        return interviewService.startInterview(id);

    }

    @PutMapping("/{id}/answer")
    public void addAnswer(final @PathVariable String id,
                          @RequestBody Map<String, String> map) {

        final String questionId = map.get("questionId");
        final String answer = map.get("answer");

        interviewService.addAnswer(id, questionId, answer);
    }


}
