package com.techolution;

import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by tdelesio on 5/23/17.
 */
@RestController
@RequestMapping(value = "/json")
public class SkillController {

    private SkillService skillService;

    public SkillController(SkillService skillService)
    {
        this.skillService = skillService;
    }


    @GetMapping("/skills")
    public List<Skill> getSkills() {

        return skillService.getAllSkills();

    }

    @GetMapping("/skills/{id}")
    public Skill getSkillById(@PathVariable String id) {

        return skillService.getSkillById(id);
    }


    @PostMapping("/skills")
    public Skill createSkill(Skill skill) {


        return skillService.createSkill(skill);
    }

    @PostMapping("/skills/questions")
    public Skill createQuestion(Question question) {


        Skill skill = skillService.addQuestion(question);
        return skill;
    }
}
