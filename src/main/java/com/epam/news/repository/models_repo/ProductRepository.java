package com.epam.news.repository.models_repo;

import com.epam.news.model.Product;
import com.epam.news.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends ReadOnlyRepo<Product> {
}
