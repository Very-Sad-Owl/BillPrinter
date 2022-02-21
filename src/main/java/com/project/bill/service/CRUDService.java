package com.project.bill.service;

import com.project.bill.model.AbstractModel;
import org.springframework.stereotype.Component;

@Component
public interface CRUDService<E extends AbstractModel> extends ReadOnlyService<E> {

    void save(E e);


}
