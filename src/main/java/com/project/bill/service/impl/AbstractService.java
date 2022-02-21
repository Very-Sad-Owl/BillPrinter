package com.project.bill.service.impl;

import com.project.bill.model.AbstractModel;
import com.project.bill.repository.ReadOnlyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractService<E extends AbstractModel, R extends ReadOnlyRepo<E>>{

    private R repository;

    @Autowired
    public final void setRepository(R repository){this.repository = repository;}
}
