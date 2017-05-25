package com.techolution.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tdelesio on 5/23/17.
 */
@Controller
public class MainController {



    @GetMapping("/")
    public ModelAndView home() {

        Map<String, Object> model = new HashMap<>();


        return new ModelAndView("home", model);
    }


}
