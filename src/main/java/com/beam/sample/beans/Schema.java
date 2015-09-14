package com.beam.sample.beans;

import com.beam.sample.business.PersonBS;
import com.beam.sample.business.UserBS;
import com.beam.sample.model.jpa.Person;
import com.beam.sample.model.mongo.Role;
import com.beam.sample.model.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by x0r on 14/09/15.
 */
@PropertySource("classpath:jpa.properties")
@Component
public class Schema {

    @Autowired
    private Environment environment;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonBS personBS;

    @Autowired
    private UserBS userBS;

    @Autowired
    private ShaPasswordEncoder encoder;

    private Class[] collections = {User.class};

    @PostConstruct
    public void onConstruct(){
        if(environment.getProperty(Constants.RECREATE_SCHEMA).equals("create")){
            dropCreateMongoSchema();
            importJpaSchema();
        }
    }

    private void dropCreateMongoSchema() {
        for(Class cls : collections){
            mongoTemplate.dropCollection(cls);
            mongoTemplate.createCollection(cls);
        }

        // perform any index operations here

        // perform imports
        importMongoSchema();
    }

    private void importJpaSchema(){
        Person person = new Person()
                .setFirstName("John")
                .setLastName("Doe");

        personBS.save(person);
    }

    private void importMongoSchema(){
        User user = new User()
                .setUsername("user")
                .setPassword(encoder.encodePassword("user", "user"));

        user.getAuthorities().add(Role.roleUser());

        userBS.save(user);
    }
}
