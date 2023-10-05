package com.project.bill.impl;

import com.project.bill.config.AppConfig;
import com.project.bill.config.WebConfig;
import com.project.bill.model.*;
import com.project.bill.service.impl.CartServiceImpl;
import com.project.bill.service.models_service.CartService;
import com.project.bill.util.MessageLocaleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:db-test.properties")
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
public class CartServiceImplTest {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService){this.cartService = cartService;}

    @Test
    public void formSlots() {
        Map<Long, Integer> goods = new HashMap<>();
        goods.put(1L, 5);
        goods.put(2L, 2);

        List<Slot> expected  = new ArrayList<>();
        expected.add(new Slot(new Product(1, "milk", 1.43, ProductDiscountType.MORE_THAN_FIVE), 5));
        expected.add(new Slot(new Product(2, "bread", 1.20, ProductDiscountType.NONE), 2));

        List<Slot> actual = cartService.formSlots(goods);

        assertEquals(expected, actual);
    }

    @Test
    public void formCart() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        Map<Long, Integer> goods = new HashMap<>();
        goods.put(1L, 5);
        goods.put(2L, 2);

        List<Slot> slots  = new ArrayList<>();
        slots.add(new Slot(new Product(1, "milk", 1.43, ProductDiscountType.MORE_THAN_FIVE), 5));
        slots.add(new Slot(new Product(2, "bread", 1.20, ProductDiscountType.NONE), 2));

        DiscountCard discountCard = new DiscountCard(2, formatter.parse("15.03.2001"), DiscountType.GOLD);

        Cashier cashier = new Cashier(2, "Olga", "Mailychko");


        Cart expected = new Cart(1, slots, discountCard, cashier);

        Cart actual = cartService.formCart(slots, discountCard, cashier);
        actual.setId(1);

        assertEquals(expected, actual);
    }

    @Test
    public void getALl(){
        List<Cart> carts = cartService.getAll();
        System.out.println(carts);
    }

    @Test
    public void findById() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        long id = 1;
        List<Slot> slots  = new ArrayList<>();
        slots.add(new Slot(new Product(1, "milk", 1.43, ProductDiscountType.TWO_AS_ONE), 6));
        DiscountCard discountCard = new DiscountCard(1, formatter.parse("15.03.2001"), DiscountType.SILVER);
        Cashier cashier = new Cashier(1, "Oleg", "Geosimp");

        Cart expected = new Cart(id, slots, discountCard, cashier);

        Cart actual = cartService.findById(id);

        assertEquals(expected, actual);
    }

}