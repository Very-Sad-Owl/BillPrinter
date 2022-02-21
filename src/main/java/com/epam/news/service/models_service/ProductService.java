package com.epam.news.service.models_service;

import com.epam.news.model.Product;
import com.epam.news.service.ReadOnlyService;
import org.springframework.stereotype.Component;

@Component
public interface ProductService extends ReadOnlyService<Product> {
}
