package com.beam.sample.model.jpa;

import com.beam.sample.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by x0r on 14/09/15.
 */
@MappedSuperclass
public class BaseJpaModel extends BaseModel {

    @Id
    @Column(name = "Id")
    @Getter
    @Setter
    protected String id;

}
