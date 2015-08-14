package com.kishor.batatebeta.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kishor.batatebeta.core.dictionary.Status;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by Nandakishor on 8/14/2015.
 */

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseResource extends ResourceSupport implements Serializable {

    protected Long id;

    private Status status;

    private String uid;

    public BaseResource() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
