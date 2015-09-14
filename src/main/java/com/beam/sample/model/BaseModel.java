package com.beam.sample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by x0r on 14/09/15.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@MappedSuperclass
@Accessors(chain = true)
public abstract class BaseModel {

    public abstract void setId(String id);

    public abstract String getId();

    public BaseModel() {
        setId(UUID.randomUUID().toString());
    }

    public BaseModel(String id) {
        setId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseModel)) {
            return false;
        }
        final BaseModel abmObj = (BaseModel) obj;
        return this.getId() != null && abmObj.getId() != null && this.getId().equals(abmObj.getId());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + (this.getId() == null ? 0 : this.getId().hashCode());
        return hash;
    }
}
