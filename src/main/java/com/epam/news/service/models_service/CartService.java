package com.epam.news.service.models_service;

import com.epam.news.model.Cart;
import com.epam.news.model.Cashier;
import com.epam.news.model.DiscountCard;
import com.epam.news.model.Slot;
import com.epam.news.service.CRUDService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CartService extends CRUDService<Cart> {

    List<Slot> formSlots(Map<Long, Integer> goods);
    Cart formCart(List<Slot> goods, DiscountCard card, Cashier cashier);
    void printBill(Cart cart, String destinationPath);

}
