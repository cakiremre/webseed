package com.beam.sample.repository.jpa;

import com.beam.sample.model.jpa.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by x0r on 14/09/15.
 */
public interface PersonRepository extends JpaRepository<Person, String> {
}
