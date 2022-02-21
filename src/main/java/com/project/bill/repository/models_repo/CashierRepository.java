package com.project.bill.repository.models_repo;

import com.project.bill.model.Cashier;
import com.project.bill.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface CashierRepository extends ReadOnlyRepo<Cashier> {
}
