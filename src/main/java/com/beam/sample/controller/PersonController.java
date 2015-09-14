package com.beam.sample.controller;

import com.beam.sample.business.PersonBS;
import com.beam.sample.model.jpa.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by x0r on 14/09/15.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonBS personBS;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> list(){
        return personBS.listPeople();
    }
}
