package com.techolution.gateway;

import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import javafx.scene.Scene;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by tdelesio on 5/24/17.
 */
@Component
public class SkillGateway {

    private RestTemplate restTemplate;

    public SkillGateway(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<Skill> getSkillsForSkillIds(String skillId)
    {
        ParameterizedTypeReference<List<Skill>> responseType = new ParameterizedTypeReference<List<Skill>>() {};
        List<Skill> skills = restTemplate.exchange("//skill-server/json/skills/{id}", HttpMethod.GET, null, responseType,skillId).getBody();
        return skills;
    }

    public List<Skill> getAllSkills()
    {
        ParameterizedTypeReference<List<Skill>> responseType = new ParameterizedTypeReference<List<Skill>>() {};
        List<Skill> skills = restTemplate.exchange("//skill-server/json/skills", HttpMethod.GET, null, responseType).getBody();
        return skills;
    }


    public Skill getSkillById(String skillId)
    {
        return restTemplate.getForObject("//skill-server/json/skills/{id}", Skill.class, skillId);
    }

    public Skill createSkill(Skill skill)
    {
        HttpEntity<Skill> request = new HttpEntity<Skill>(skill) {};
        skill = restTemplate.postForObject("//skill-server/json/skills", request, Skill.class);
        return skill;
    }

    public Skill addQuestion(Question question)
    {
        HttpEntity<Question> request = new HttpEntity<Question>(question) {};
        Skill skill = restTemplate.postForObject("//skill-server/json/skills/questions", request, Skill.class);
        return skill;
    }

}
