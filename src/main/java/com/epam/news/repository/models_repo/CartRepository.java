package com.epam.news.repository.models_repo;

import com.epam.news.model.Cart;
import com.epam.news.repository.CRUDRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartRepository extends CRUDRepository<Cart> {

    void printBill(Cart cart, String path);

}
