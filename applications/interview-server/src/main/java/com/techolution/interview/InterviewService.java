package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * Created by tdelesio on 5/24/17.
 */
@Service
public class InterviewService {

    final Logger log = LoggerFactory.getLogger(InterviewService.class);

    private InterviewRepository interviewRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PositionGateway positionGateway;

    @Autowired
    private SkillGateway skillGateway;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    public List<Interview> getAllInterviews() {

        List<Interview> interviews = new ArrayList<>();
        interviewRepository.findAll().forEach(interviews::add);
        return interviews;
    }

    public Interview getInterviewById(String id) {
        Interview interview = interviewRepository.findOne(id);
        return interview;
    }

    public Interview createInterview(Interview interview) {
        String id = UUID.randomUUID().toString();
        interview.setId(id);
        return interviewRepository.save(interview);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Returns list of questions associated to this position.
     *
     * @return
     */
    public Map<String, Object> startInterview(final String id) {

        Map<String, Object> model = new HashMap<>();
        final Interview interview = interviewRepository.findOne(id);

        model.put("interview", interview);
        final String positionId = interview.getPositionId();

        if (StringUtils.isBlank(positionId)) {
            return model;
        }

        final Position position = positionGateway.getPositionById(positionId);

        model.put("position", position);

        try {

            final Set<String> skillIds = position.getSkills();
            final Skill[] skills = skillGateway.getSkillsBySkillIds(skillIds);

            if (ArrayUtils.isEmpty(skills)) {
                return model;
            }

            final Set<Question> questions = new HashSet<>();

            for (final Skill skill : skills) {
                questions.addAll(skill.getQuestions());
            }

            model.put("questions", questions);

            return model;
        } catch (Exception ex) {
            log.error("Exception occurred accessing positions by id - {}", positionId, ex);
        }
        return null;
    }


    public void setPositionGateway(PositionGateway positionGateway) {
        this.positionGateway = positionGateway;
    }

    public void setSkillGateway(SkillGateway skillGateway) {
        this.skillGateway = skillGateway;
    }
}
