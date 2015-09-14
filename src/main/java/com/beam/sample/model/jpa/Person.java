package com.beam.sample.model.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by x0r on 14/09/15.
 */

@Entity
@Table(name = "Person")
@Accessors(chain = true)
public class Person extends BaseJpaModel {

    @Getter
    @Setter
    @Column(name = "FirstName")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "LastName")
    private String lastName;

}
