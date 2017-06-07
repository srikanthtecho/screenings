package com.techolution.interview;

import com.techolution.position.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class PositionGateway {


    final Logger log = LoggerFactory.getLogger(PositionGateway.class);

    @Autowired
    private RestTemplate restTemplate;


    public Position getPositionById(final String positionId) {
        log.info("Requesting position service to access position information for the  id - ",
            positionId);

        final Position position = restTemplate.getForObject(
            "//position-server/json/positions/{id}",
            Position.class, positionId);

        log.info("Response received from position service  for  position {}", position.getId());

        return position;
    }
}
