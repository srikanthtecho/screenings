package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tdelesio on 5/24/17.
 */
@Component
public class InterviewGateway {


    private final Logger logger = LoggerFactory.getLogger(InterviewGateway.class);

    private final RestTemplate restTemplate;

    public InterviewGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

        ParameterizedTypeReference<List<Interview>> responseType = new ParameterizedTypeReference<List<Interview>>() {
        };
        List<Interview> interviews = restTemplate.exchange("http://interview-server/json/interviews", HttpMethod.GET, null, responseType).getBody();
        return interviews;

    }

    public Interview createInterview(Interview interview) {

        HttpEntity<Interview> request = new HttpEntity<Interview>(interview) {
        };
        interview = restTemplate.postForObject("//interview-server/json/interviews", request,
                Interview.class);
        return interview;

    }

    public Interview getInterviewById(String id) {
        return restTemplate.getForObject("http://interview-server/json/interviews/{id}", Interview.class, id);
    }

    public Map<String, Object> startInterview(final String id) {

        try {
            final Map<String, Object>  model = restTemplate.getForObject("http://interview-server/json/interviews/{id}/start",
                Map.class, id);
            return model;
        } catch (Exception ex) {
            logger.error("Exception occurred for starting interview with id {} ", id, ex);
        }
        return null;
    }
}
