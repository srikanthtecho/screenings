package com.techolution.interview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/24/17.
 */
@RestController
@RequestMapping(value = "/json")
public class InterviewRestController {

    private InterviewService interviewService;

    public InterviewRestController(InterviewService interviewService)
    {
        this.interviewService = interviewService;
    }


    @PostMapping("/interviews")
    public Interview createInterview(@RequestBody Interview interview)
    {
        return interviewService.createInterview(interview);

    }

    @GetMapping("/interviews")
    public List<Interview> getAllInterviews()
    {

        return interviewService.getAllInterviews();
    }

    @GetMapping("interviews/{id}")
    public Interview interviewDetails(@PathVariable String id)
    {
        return interviewService.getInterviewById(id);

    }

//    @GetMapping("interviews/{id}/start")
//    public Interview giveInterview(@PathVariable String id)
//    {
//        Map<String, Object> model = new HashMap<>();
//
//
//        return new ModelAndView("interview-start", model);
//    }
}
