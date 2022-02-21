package com.epam.news.service;

import com.epam.news.model.AbstractModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReadOnlyService<E extends AbstractModel> {

    E findById(long id);

    List<E> getAll();

}
