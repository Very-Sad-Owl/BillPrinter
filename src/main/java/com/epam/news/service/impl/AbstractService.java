package com.epam.news.service.impl;

import com.epam.news.model.AbstractModel;
import com.epam.news.repository.ReadOnlyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractService<E extends AbstractModel, R extends ReadOnlyRepo<E>>{

    private R repository;

    @Autowired
    public final void setRepository(R repository){this.repository = repository;}
}
