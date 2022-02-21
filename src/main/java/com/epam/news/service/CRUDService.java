package com.epam.news.service;

import com.epam.news.model.AbstractModel;
import org.springframework.stereotype.Component;

@Component
public interface CRUDService<E extends AbstractModel> extends ReadOnlyService<E> {

    void save(E e);


}
