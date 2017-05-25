package com.techolution.position;

import com.techolution.skill.SkillGateway;
import com.techolution.skill.Skill;
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
 * Created by tdelesio on 5/25/17.
 */
@Controller
public class PositionController {
    private PositionGateway positionGateway;
    private SkillGateway skillGateway;

    public PositionController(PositionGateway positionGateway, SkillGateway skillGateway)
    {
        this.positionGateway = positionGateway;
        this.skillGateway = skillGateway;
    }

    @ModelAttribute("position")
    public Position getPositionObject() {
        return new Position();
    }

    @GetMapping("/positions")
    public ModelAndView getPositions() {

        Map<String, Object> model = new HashMap<>();

        List<Position> positions = positionGateway.getAllPositions();
        model.put("positions", positions);

        return new ModelAndView("position", model);
    }

    private Map<String, Object> preloadPositionDetail(String positionId)
    {
        Map<String, Object> model = new HashMap<>();

        Position position = positionGateway.getPositionById(positionId);
        model.put("position", position);

        List<Skill> skills = skillGateway.getAllSkills();
        model.put("skills", skills);

        return model;
    }

    @GetMapping("/positions/{id}")
    public ModelAndView getPositionById(@PathVariable String id) {


        return new ModelAndView("positiondetail", preloadPositionDetail(id));
    }

    private Map<String, Object> preloadPositions()
    {
        Map<String, Object> model = new HashMap<>();

        List<Position> positions = positionGateway.getAllPositions();
        model.put("positions", positions);

        return model;
    }
    @PostMapping("/positions")
    public ModelAndView createPosition(Position position) {

        positionGateway.createPosition(position);

        return new ModelAndView("position", preloadPositions());
    }



    @PostMapping("/positions/link")
    public ModelAndView linkSkillsToPosition(Position position) {



        positionGateway.linkSkillsToPosition(position);

        return new ModelAndView("position", preloadPositions());
    }
}
