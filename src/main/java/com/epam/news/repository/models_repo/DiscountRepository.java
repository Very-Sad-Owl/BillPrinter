package com.epam.news.repository.models_repo;

import com.epam.news.model.DiscountCard;
import com.epam.news.repository.ReadOnlyRepo;
import org.springframework.stereotype.Component;

@Component
public interface DiscountRepository extends ReadOnlyRepo<DiscountCard> {
}
