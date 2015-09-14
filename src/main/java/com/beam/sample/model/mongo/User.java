package com.beam.sample.model.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by x0r on 14/09/15.
 */
@Document(collection = "User")
@TypeAlias("user")
@Accessors(chain = true)
public class User extends BaseMongoModel implements UserDetails {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean accountNonExpired = true;

    @Getter
    @Setter
    private boolean accountNonLocked = true;

    @Getter
    @Setter
    private boolean credentialsNonExpired = true;

    @Getter
    @Setter
    private boolean enabled = true;

    @Getter
    @Setter
    private Set<Role> authorities = new HashSet<>();

}
