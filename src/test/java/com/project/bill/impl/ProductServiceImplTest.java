package com.project.bill.impl;

import com.project.bill.config.AppConfig;
import com.project.bill.model.Product;
import com.project.bill.model.ProductDiscountType;
import com.project.bill.service.impl.ProductServiceImpl;
import com.project.bill.service.models_service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {ProductServiceImpl.class, AppConfig.class})
public class ProductServiceImplTest {

    private ProductService productService;

    @Autowired
    public void setProductRepository(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void findById() {
        Product expected = new Product(1, "milk", 1.43,
                ProductDiscountType.valueOf("more_than_five".toUpperCase()));

        Product actual = productService.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    public void getAll() {
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(new Product(1, "milk", 1.43,
                ProductDiscountType.valueOf("more_than_five".toUpperCase())));
        expected.add(new Product(2, "bread", 1.20,
                ProductDiscountType.valueOf("none".toUpperCase())));

        List<Product> actual = productService.getAll();

        assertEquals(expected, actual);
    }
}