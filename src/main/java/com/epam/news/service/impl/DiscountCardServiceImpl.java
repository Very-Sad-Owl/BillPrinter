package com.epam.news.service.impl;

import com.epam.news.model.DiscountCard;
import com.epam.news.repository.models_repo.DiscountRepository;
import com.epam.news.service.models_service.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCardServiceImpl
        extends AbstractService<DiscountCard, DiscountRepository>
        implements DiscountCardService {

    private DiscountRepository discountRepository;

    @Autowired
    public void setDiscountRepository(DiscountRepository discountRepository){this.discountRepository = discountRepository;}

    @Override
    public DiscountCard findById(long id) {
        return discountRepository.findById(id);
    }

    @Override
    public List<DiscountCard> getAll() {
        return discountRepository.getAll();
    }
}
