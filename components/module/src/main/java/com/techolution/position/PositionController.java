package com.techolution.position;

import com.techolution.module.Skill;
import com.techolution.module.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/23/17.
 */
@Controller
public class PositionController {

    private PositionService positionService;
    private SkillService skillService;

    public PositionController(PositionService positionService, SkillService skillService)
    {
        this.positionService = positionService;
        this.skillService = skillService;
    }

    @GetMapping("/positions")
    public ModelAndView getPositions() {

        Map<String, Object> model = new HashMap<>();

        List<Position> positions = positionService.getAllPositions();
        model.put("positions", positions);

        return new ModelAndView("position", model);
    }

    @GetMapping("/positions/{id}")
    public ModelAndView getPositionById(@PathVariable String id) {

        Map<String, Object> model = new HashMap<>();

        Position position = positionService.getPositionById(id);
        model.put("position", position);

        List<Skill> skills = skillService.getAllSkills();
        model.put("skills", skills);

        return new ModelAndView("positiondetail", model);
    }

    @PostMapping("/positions")
    public ModelAndView createPosition(Position position) {

        Map<String, Object> model = new HashMap<>();

        positionService.createPosition(position);

        List<Position> positions = positionService.getAllPositions();
        model.put("positions", positions);

        return new ModelAndView("position", model);
    }

    @ModelAttribute("position")
    public Position getPositionObject() {
        return new Position();
    }
}
