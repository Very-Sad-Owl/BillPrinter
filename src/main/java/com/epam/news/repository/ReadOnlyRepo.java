package com.epam.news.repository;

import com.epam.news.model.AbstractModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReadOnlyRepo<E extends AbstractModel> {

    E findById(long id);

    List<E> getAll();
}
