package com.beam.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by x0r on 14/09/15.
 */
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
