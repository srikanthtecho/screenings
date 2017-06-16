package com.techolution.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by tdelesio on 5/24/17.
 */
@Component
public class InterviewGateway {


    private final Logger log = LoggerFactory.getLogger(InterviewGateway.class);

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
        List<Interview> interviews = restTemplate.exchange("//interview-server/json/interviews", HttpMethod.GET, null, responseType).getBody();
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
            final Map<String, Object> model = restTemplate.getForObject("http://interview-server/json/interviews/{id}/start",
                Map.class, id);
            return model;
        } catch (Exception ex) {
            log.error("Exception occurred for starting interview with id {} ", id, ex);
        }
        return null;
    }

    public Response addAnswer(final String id,
                              final Map<String, String> map) {

        final Response response = new Response();

        try {
            final Map<String, String> params = new HashMap<>();

            params.put("id", id);

            log.info("Requesting interview service to add  answer to the question having ID {}", params.get("questionId"));

            restTemplate.put(
                "//interview-server/json/interviews/{id}/answer",
                map, params);

            log.info("Received response from interview service to add answer to the question having ID {}", params.get("questionId"));

            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.toString());

            return response;

        } catch (Exception ex) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(ex.getMessage());
            log.error("Exception occurred for starting interview with id {} ", id, ex);
        }
        return null;
    }

}
