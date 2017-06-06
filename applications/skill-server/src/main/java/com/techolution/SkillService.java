package com.techolution;

import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;git
import java.util.List;
import java.util.Set;

/**
 * Created by tdelesio on 5/23/17.
 */
@Service
public class SkillService {

    private SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;

    }

    public Skill getSkillById(String id) {
        return skillRepository.findOne(id);
    }

    public Skill addQuestion(Question question) {
        Skill skill = skillRepository.findOne(question.getSkillId());
        skill.addQuestion(question);

        skillRepository.save(skill);
        return skill;
    }

    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();

        skillRepository.findAll().forEach(skills::add);
        return skills;
    }

    public List<Skill> getSkillsForSkillIds(Set<String> skillIds) {
        List<Skill> skills = new ArrayList<>();
        if (skillIds == null)
            return Collections.EMPTY_LIST;

        skillRepository.findAll(skillIds).forEach(skills::add);
        return skills;
    }


}
