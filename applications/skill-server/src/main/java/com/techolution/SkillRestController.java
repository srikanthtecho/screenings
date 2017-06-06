package com.techolution;

import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by tdelesio on 5/23/17.
 */
@RestController
@RequestMapping(value = "/json")
    //, produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class SkillRestController {

    private SkillService skillService;

    public SkillRestController(SkillService skillService)
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
    public Skill createSkill(@RequestBody Skill skill) {


        return skillService.createSkill(skill);
    }

    @GetMapping("/skills/ids/{skillIds}")
    public List<Skill>  getSkillsForIds(@PathVariable Set<String> skillIds) {

        return  skillService.getSkillsForSkillIds(skillIds);

    }

    @PostMapping("/skills/questions")
    public Skill createQuestion(@RequestBody Question question) {


        Skill skill = skillService.addQuestion(question);
        return skill;
    }

}
