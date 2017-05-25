package com.techolution.skill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/25/17.
 */
@Controller
public class SkillController {

    private SkillGateway skillGateway;

    public SkillController(SkillGateway skillGateway)
    {
        this.skillGateway = skillGateway;
    }

    @ModelAttribute("skill")
    public Skill getSKillObject() {
        return new Skill();
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
}
