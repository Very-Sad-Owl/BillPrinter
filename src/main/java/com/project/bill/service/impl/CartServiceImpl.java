package com.project.bill.service.impl;

import com.project.bill.model.Cart;
import com.project.bill.model.Cashier;
import com.project.bill.model.DiscountCard;
import com.project.bill.model.Slot;
import com.project.bill.repository.models_repo.CartRepository;
import com.project.bill.service.models_service.CartService;
import com.project.bill.service.models_service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CartServiceImpl extends AbstractService<Cart, CartRepository> implements CartService {

    private ProductService productService;
    private CartRepository cartRepository;

    @Autowired
    public void setCartRepo(CartRepository cartRepository){this.cartRepository = cartRepository;}

    @Autowired
    public void setProductService(ProductService productService){this.productService = productService;}

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findById(long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.getAll();
    }

    @Override
    public List<Slot> formSlots(Map<Long, Integer> goods) {
        List<Slot> slots = new ArrayList<>();
        for(Map.Entry<Long,Integer> entry : goods.entrySet()){
            slots.add(new Slot(productService.findById(entry.getKey()), entry.getValue()));
        }
        return slots;
    }

    @Override
    public Cart formCart(List<Slot> goods, DiscountCard card, Cashier cashier) {
        return new Cart(new Random(System.currentTimeMillis()).nextLong(), goods, card, cashier);

    }

    @Override
    public void printBill(Cart cart, String destinationPath) {
        cartRepository.printBill(cart, destinationPath);
    }
}
