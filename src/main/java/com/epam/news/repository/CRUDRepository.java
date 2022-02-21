package com.epam.news.repository;

import com.epam.news.model.AbstractModel;
import org.springframework.stereotype.Component;

@Component
public interface CRUDRepository<E extends AbstractModel> extends ReadOnlyRepo<E> {

    void save(E e);
}
