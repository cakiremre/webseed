package com.beam.sample.model.mongo;

import com.beam.sample.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


/**
 * Created by x0r on 14/09/15.
 */
public abstract class BaseMongoModel extends BaseModel {

    @Id
    @Getter
    @Setter
    protected String id;

}
