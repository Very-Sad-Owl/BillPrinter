package com.project.bill.service.models_service;

import com.project.bill.model.Product;
import com.project.bill.service.ReadOnlyService;
import org.springframework.stereotype.Component;

@Component
public interface ProductService extends ReadOnlyService<Product> {
}
