package com.epam.news.repository.impl;

import by.epam.training.jwd.task03.entity.Node;
import by.epam.training.jwd.task03.service.exception.ServiceException;
import com.epam.news.exception.CashierNotFoundException;
import com.epam.news.exception.ReadingException;
import com.epam.news.model.Cashier;
import com.epam.news.repository.models_repo.CashierRepository;
import com.epam.news.util.node_converter.NodeWorker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CashierRepositoryImpl extends AbstractRepository implements CashierRepository {

    @Override
    public Cashier findById(long id) {
        List<Cashier> nodes = getAll();
        for (Cashier product : nodes){
            if (product.getId() == id){
                return product;
            }
        }
        throw new CashierNotFoundException(locale.getMessage("error.cashier_not_found"));
    }

    @Override
    public List<Cashier> getAll() {
        Node node;
        NodeWorker<Cashier> worker = workerFactory.getCashierWorker();
        List<Cashier> products = new ArrayList<>();
        try {
            node = nodeTreeBuilder.parseXML(env.getProperty("path.cashier"));
            worker.nodeToList(node, products);
        } catch (ServiceException e) {
            throw new ReadingException(locale.getMessage("error.reading"));
        }
        return products;
    }
}
