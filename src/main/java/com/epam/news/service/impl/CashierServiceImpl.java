package com.epam.news.service.impl;

import com.epam.news.model.Cashier;
import com.epam.news.repository.models_repo.CashierRepository;
import com.epam.news.service.models_service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierServiceImpl extends AbstractService<Cashier, CashierRepository> implements CashierService {

    private CashierRepository cashierRepository;

    @Autowired
    public void setCashierRepository(CashierRepository cashierRepository){this.cashierRepository = cashierRepository;}

    @Override
    public Cashier findById(long id) {
        return cashierRepository.findById(id);
    }

    @Override
    public List<Cashier> getAll() {
        return cashierRepository.getAll();
    }
}
