package com.kishor.batatebeta.rest.resource.assembler;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Nandakishor on 8/14/2015.
 */

@Component
public abstract class EntityAssembler<T extends ResourceSupport, D> {

    @Autowired
    private Mapper mapper;

    public EntityAssembler() {}

    public D toEntity(T resource, Class<D> dClass) {
        return mapper.map(resource, dClass);
    }
}
