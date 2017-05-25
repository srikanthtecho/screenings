package com.techolution.controller;

import com.techolution.gateway.InterviewGateway;
import com.techolution.gateway.PositionGateway;
import com.techolution.gateway.SkillGateway;
import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/23/17.
 */
@Controller
public class MainController {

    private SkillGateway skillGateway;
    private InterviewGateway interviewGateway;
    private PositionGateway positionGateway;

    public MainController(SkillGateway skillGateway, InterviewGateway interviewGateway, PositionGateway positionGateway)
    {
        this.skillGateway = skillGateway;
        this.interviewGateway = interviewGateway;
        this.positionGateway = positionGateway;

    }

    @ModelAttribute("skill")
    public Skill getSKillObject() {
        return new Skill();
    }

    @ModelAttribute("position")
    public Position getPositionObject() {
        return new Position();
    }

    @GetMapping("/")
    public ModelAndView home() {

        Map<String, Object> model = new HashMap<>();


        return new ModelAndView("home", model);
    }


    @GetMapping("/skills")
    public ModelAndView getSkills(@RequestParam(required = false, value = "position_id") String positionId) {

        Map<String, Object> model = new HashMap<>();
        List<Skill> skills;
//        if (positionId != null)
//        {
//            Position position = positionGateway.getPositionById(positionId);
//            skills = skillGateway.getSkillsForSkillIds(position.getSkills());
//        }
//        else {
            skills = skillGateway.getAllSkills();
//        }
        model.put("skills", skills);

        return new ModelAndView("skill", model);
    }

    @GetMapping("/skills/{id}")
    public ModelAndView getSkillById(@PathVariable String id) {

        return new ModelAndView("skilldetail", preloadSkillDetails(id));
    }

    private Map<String, Object> preloadSkillDetails(String skillId)
    {
        Map<String, Object> model = new HashMap<>();

        Skill skill = skillGateway.getSkillById(skillId);
        Question question = new Question();
        question.setSkillId(skill.getId());

        model.put("skill", skill);
        model.put("question", question);

        return model;
    }

    @PostMapping("/skills")
    public ModelAndView createSkill(Skill skill) {


        skill = skillGateway.createSkill(skill);

//        List<Skill> skills = skillService.getAllSkills();
//        model.put("skills", skills);

        return new ModelAndView("skilldetail", preloadSkillDetails(skill.getId()));
    }

    @PostMapping("/skills/questions")
    public ModelAndView createQuestion(Question question) {

        Map<String, Object> model = new HashMap<>();

        Skill skill = skillGateway.addQuestion(question);

        question = new Question();
        question.setSkillId(skill.getId());

        model.put("question", question);
        model.put("skill", skill);

        return new ModelAndView("skilldetail", model);
    }

    @GetMapping("/positions")
    public ModelAndView getPositions() {

        Map<String, Object> model = new HashMap<>();

        List<Position> positions = positionGateway.getAllPositions();
        model.put("positions", positions);

        return new ModelAndView("position", model);
    }

    private Map<String, Object> preloadPositionDetail(String positionId)
    {
        Map<String, Object> model = new HashMap<>();

        Position position = positionGateway.getPositionById(positionId);
        model.put("position", position);

        List<Skill> skills = skillGateway.getAllSkills();
        model.put("skills", skills);

        return model;
    }

    @GetMapping("/positions/{id}")
    public ModelAndView getPositionById(@PathVariable String id) {


        return new ModelAndView("positiondetail", preloadPositionDetail(id));
    }

    private Map<String, Object> preloadPositions()
    {
        Map<String, Object> model = new HashMap<>();

        List<Position> positions = positionGateway.getAllPositions();
        model.put("positions", positions);

        return model;
    }
    @PostMapping("/positions")
    public ModelAndView createPosition(Position position) {

        positionGateway.createPosition(position);

        return new ModelAndView("position", preloadPositions());
    }



    @PostMapping("/positions/link")
    public ModelAndView linkSkillsToPosition(Position position) {



        positionGateway.linkSkillsToPosition(position);

        return new ModelAndView("position", preloadPositions());
    }
}
