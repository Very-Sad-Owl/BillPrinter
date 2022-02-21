package com.project.bill.service.impl;

import com.project.bill.model.Cashier;
import com.project.bill.repository.models_repo.CashierRepository;
import com.project.bill.service.models_service.CashierService;
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
