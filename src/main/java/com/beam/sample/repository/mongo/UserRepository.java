package com.beam.sample.repository.mongo;

import com.beam.sample.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by x0r on 14/09/15.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
