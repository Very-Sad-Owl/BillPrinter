package com.project.bill.repository.models_repo;

import com.project.bill.model.Product;
import com.project.bill.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends ReadOnlyRepo<Product> {
}
