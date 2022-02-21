package com.project.bill.repository;

import com.project.bill.model.AbstractModel;
import org.springframework.stereotype.Component;

@Component
public interface CRUDRepository<E extends AbstractModel> extends ReadOnlyRepo<E> {

    void save(E e);
}
