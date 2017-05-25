package com.techolution.position;

import com.techolution.position.Position;
import com.techolution.skill.Skill;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
public class PositionGateway {

    private RestTemplate restTemplate;

    public PositionGateway(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<Position> getAllPositions()
    {
        ParameterizedTypeReference<List<Position>> responseType = new ParameterizedTypeReference<List<Position>>() {};
        List<Position> positions = restTemplate.exchange("http://position-server/json/positions", HttpMethod.GET, null, responseType).getBody();
        return positions;
    }

    public Position getPositionById(String positionId)
    {
        return restTemplate.getForObject("//position-server/json/positions/{id}", Position.class, positionId);
    }

    public Position createPosition(Position position)
    {
        HttpEntity<Position> request = new HttpEntity<Position>(position) {};
        position = restTemplate.postForObject("//position-server/json/positions", request, Position.class);
        return position;
    }

    public Position linkSkillsToPosition(Position position)
    {
        HttpEntity<Position> request = new HttpEntity<Position>(position) {};
        position = restTemplate.postForObject("//position-server/json/positions/link", request, Position.class);
        return position;
    }
}
