package com.beam.sample.model.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by x0r on 14/09/15.
 */
@Accessors(chain = true)
public class Role implements GrantedAuthority {

    @Getter
    @Setter
    private String name;

    public String getAuthority() {
        return name;
    }

    public static Role roleUser() {
        return new Role()
                .setName("ROLE_USER");
    }

    public static Role roleAdmin() {
        return new Role()
                .setName("ROLE_ADMIN");
    }
}
