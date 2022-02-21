package com.epam.news.repository.models_repo;

import com.epam.news.model.Cashier;
import com.epam.news.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface CashierRepository extends ReadOnlyRepo<Cashier> {
}
