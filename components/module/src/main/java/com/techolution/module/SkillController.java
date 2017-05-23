package com.techolution.module;

import com.techolution.position.Position;
import com.techolution.position.PositionService;
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
public class SkillController {

    private SkillService skillService;
    private PositionService positionService;

    public SkillController(SkillService skillService, PositionService positionService)
    {
        this.skillService = skillService;
        this.positionService = positionService;
    }

    @ModelAttribute("skill")
    public Skill getSKillObject() {
        return new Skill();
    }
//
//    @ModelAttribute("question")
//    public Question getQuestionObject() {
//        return new Question();
//    }

    @GetMapping("/skills")
    public ModelAndView getSkills(@RequestParam(required = false, value = "position_id") String positionId) {

        Map<String, Object> model = new HashMap<>();
        List<Skill> skills;
        if (positionId != null)
        {
            Position position = positionService.getPositionById(positionId);
            skills = skillService.getSkillsForSkillIds(position.getSkills());
        }
        else {
            skills = skillService.getAllSkills();
        }
        model.put("skills", skills);

        return new ModelAndView("skill", model);
    }

    @GetMapping("/skills/{id}")
    public ModelAndView getSkillById(@PathVariable String id) {

        Map<String, Object> model = new HashMap<>();

        Skill skill = skillService.getSkillById(id);
        Question question = new Question();
        question.setSkillId(skill.getId());

        model.put("skill", skill);
        model.put("question", question);

        return new ModelAndView("skilldetail", model);
    }

    @PostMapping("/skills")
    public ModelAndView createPosition(Skill skill) {

        Map<String, Object> model = new HashMap<>();

        skillService.createSkill(skill);

        List<Skill> skills = skillService.getAllSkills();
        model.put("skills", skills);

        return new ModelAndView("skill", model);
    }

    @PostMapping("/skills/questions")
    public ModelAndView createQuestion(Question question) {

        Map<String, Object> model = new HashMap<>();

        Skill skill = skillService.addQuestion(question);

        question = new Question();
        question.setSkillId(skill.getId());

        model.put("question", question);
        model.put("skill", skill);

        return new ModelAndView("skilldetail", model);
    }
}
