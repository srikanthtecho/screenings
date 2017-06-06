package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * Created by tdelesio on 5/24/17.
 */
@Service
public class InterviewService {

    final Logger log = LoggerFactory.getLogger(InterviewService.class);

    private InterviewRepository interviewRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @PostConstruct
    public void init() {

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });
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
     * Returns list of questions associated to this position
     *
     * @return
     */
    public Map<String, Object> startInterview(final String id) {

        Map<String, Object> model  = new HashMap<>();
        final Interview interview = interviewRepository.findOne(id);
        final String positionId = interview.getPositionId();

        try {

            log.info("Requesting position service to access position information for the interview id - ",
                interview.getId());

            /**
             * TODO Replace hardcoded IP addresses with Eureka names
             * Bug opened for Eureka heart beat issue
             */
            final Position position = restTemplate.getForObject(
                "http://localhost:8083/json/positions/{id}",
                Position.class, positionId);

            log.info("Response received from position service  for interview {} and position {}",
                interview.getId(), position.getId());

            final Set<String> skillIds = position.getSkills();

            final String params = StringUtils.join(skillIds, ",");
            final Set<Question> questions = new HashSet<>();

            String url = "http://localhost:8082/json/skills/ids/" + params;

            log.info("Requesting skill service to access skill information of the position id - {}",
                position.getId());

            Skill[] skills = restTemplate.getForObject(url, Skill[].class);

            log.info("Response received from skill service  for position {}",
                position.getId());

            /**
             * TODO replace this with java 8 streams and collectors.
             */
            for (final Skill skill : skills) {
                questions.addAll(skill.getQuestions());
            }
            model.put("interview", interview);
            model.put("position", position);
            model.put("questions", questions);

            return model;
        } catch (Exception ex) {
            log.error("Exception occurred accessing positions by id - {}", positionId, ex);
        }
        return null;
    }

}
