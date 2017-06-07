package com.techolution.interview;

import com.techolution.skill.Skill;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class SkillGateway {

    final Logger log = LoggerFactory.getLogger(SkillGateway.class);

    @Autowired
    private RestTemplate restTemplate;

    public Skill[] getSkillsBySkillIds(final Set<String> skillIds) {

        Skill[] skills = new Skill[skillIds.size()];

        if (CollectionUtils.isEmpty(skillIds)) {
            return skills;
        }

        final String params = StringUtils.join(skillIds, ",");
        final String url = "//skill-server/json/skills/ids/" + params;

        log.info("Requesting skill service to access skill information of the skill ids - {}",
            skillIds);

         skills = restTemplate.getForObject(url, Skill[].class);

        log.info("Response received from skill service  for skill ids {}", skillIds);

        return skills;
    }
}
