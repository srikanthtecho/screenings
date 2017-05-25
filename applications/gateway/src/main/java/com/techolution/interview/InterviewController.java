package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.position.PositionGateway;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/25/17.
 */
@Controller
public class InterviewController {

    private InterviewGateway interviewGateway;
    private PositionGateway positionGateway;

    public InterviewController(InterviewGateway interviewGateway, PositionGateway positionGateway)
    {
        this.positionGateway = positionGateway;
        this.interviewGateway = interviewGateway;
    }

    @ModelAttribute("interview")
    public Interview getInterviewObject() {
        return new Interview();
    }

    @ModelAttribute("positions")
    public List<Position> getAllPositions()
    {
        return positionGateway.getAllPositions();
    }

    private Map<String, Object> preloadInterview()
    {
        Map<String, Object> model = new HashMap<>();

        List<Interview> interviews = interviewGateway.getAllInterviews();
        model.put("interviews", interviews);

        return model;
    }

    @PostMapping("/interviews")
    public ModelAndView createInterview(Interview interview)
    {
        interview = interviewGateway.createInterview(interview);

        return new ModelAndView("interview", preloadInterview());
    }

    @GetMapping("/interviews")
    public ModelAndView getAllInterviews()
    {

        return new ModelAndView("interview", preloadInterview());
    }

    @GetMapping("interviews/{id}")
    public ModelAndView interviewDetails(@PathVariable String id)
    {
        Map<String, Object> model = new HashMap<>();

        Interview interview = interviewGateway.getInterviewById(id);
        model.put("interview", interview);

        return new ModelAndView("interview-details", model);
    }

    @GetMapping("interviews/{id}/start")
    public ModelAndView giveInterview(@PathVariable String id)
    {
        Map<String, Object> model = new HashMap<>();


        return new ModelAndView("interview-start", model);
    }
}
