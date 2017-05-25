package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by tdelesio on 5/24/17.
 */
@Component
public class InterviewGateway {


    private RestTemplate restTemplate;

    public InterviewGateway(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<Interview> getAllInterviews()
    {
        ParameterizedTypeReference<List<Interview>> responseType = new ParameterizedTypeReference<List<Interview>>() {};
        List<Interview> interviews = restTemplate.exchange("http://interview-server/json/interviews", HttpMethod.GET, null, responseType).getBody();
        return interviews;

    }

    public Interview createInterview(Interview interview)
    {
        HttpEntity<Interview> request = new HttpEntity<Interview>(interview) {};
        interview = restTemplate.postForObject("//interview-server/json/interviews", request, Interview.class);
        return interview;

    }

    public Interview getInterviewById(String id)
    {
        return restTemplate.getForObject("http://interview-server/json/interviews/{id}", Interview.class, id);
    }
}
