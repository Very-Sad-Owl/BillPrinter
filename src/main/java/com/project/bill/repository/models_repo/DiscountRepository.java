package com.project.bill.repository.models_repo;

import com.project.bill.model.DiscountCard;
import com.project.bill.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface DiscountRepository extends ReadOnlyRepo<DiscountCard> {
}
