package com.techolution.controller;

import com.techolution.position.Position;
import com.techolution.position.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tdelesio on 5/23/17.
 */
@Controller
public class MainController {



//    MainController(PositionService positionService)
//    {
//        this.positionService = positionService;
//    }

    @GetMapping("/")
    public ModelAndView home() {

        Map<String, Object> model = new HashMap<>();


        return new ModelAndView("home", model);
    }



    @GetMapping("/crete/skill")
    public ModelAndView createSkill() {

        Map<String, Object> model = new HashMap<>();


        return new ModelAndView("create-skill", model);
    }
}
