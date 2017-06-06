package com.techolution;

import com.techolution.position.Position;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tdelesio on 5/23/17.
 */
@RestController
@RequestMapping(value = "/json")
public class PositionRestController {

    private PositionService positionService;

    public PositionRestController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions")
    public List<Position> getPositions() {

        List<Position> positions = positionService.getAllPositions();
        return positions;

    }


    @GetMapping("/positions/{id}")
    public Position getPositionById(@PathVariable String id) {

        Position position = positionService.getPositionById(id);
        return position;

    }

    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {

        return positionService.createPosition(position);

    }


    @PostMapping("/positions/link")
    public Position linkSkillsToPosition(@RequestBody Position position) {

        return positionService.linkSkillsToPosition(position);

    }
}
