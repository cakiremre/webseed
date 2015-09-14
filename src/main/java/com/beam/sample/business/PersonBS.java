package com.beam.sample.business;

import com.beam.sample.model.jpa.Person;
import com.beam.sample.repository.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by x0r on 14/09/15.
 */
@Transactional
@Service
public class PersonBS {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> listPeople(){
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.saveAndFlush(person);
    }
}
