package com.project.bill.repository.models_repo;

import com.project.bill.model.Cart;
import com.project.bill.repository.CRUDRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartRepository extends CRUDRepository<Cart> {

    void printBill(Cart cart, String path);

}
