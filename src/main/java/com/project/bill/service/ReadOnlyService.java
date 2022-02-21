package com.project.bill.service;

import com.project.bill.model.AbstractModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReadOnlyService<E extends AbstractModel> {

    E findById(long id);

    List<E> getAll();

}
