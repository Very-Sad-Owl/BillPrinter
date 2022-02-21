package com.project.bill.service.models_service;

import com.project.bill.model.Cart;
import com.project.bill.model.Cashier;
import com.project.bill.model.DiscountCard;
import com.project.bill.model.Slot;
import com.project.bill.service.CRUDService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CartService extends CRUDService<Cart> {

    List<Slot> formSlots(Map<Long, Integer> goods);
    Cart formCart(List<Slot> goods, DiscountCard card, Cashier cashier);
    void printBill(Cart cart, String destinationPath);

}
