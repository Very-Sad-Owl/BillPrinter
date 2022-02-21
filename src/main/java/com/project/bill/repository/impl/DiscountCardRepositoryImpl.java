package com.project.bill.repository.impl;

import by.epam.training.jwd.task03.entity.Node;
import by.epam.training.jwd.task03.service.exception.ServiceException;
import com.project.bill.exception.CardNotFoundException;
import com.project.bill.exception.ReadingException;
import com.project.bill.model.DiscountCard;
import com.project.bill.repository.models_repo.DiscountRepository;
import com.project.bill.util.node_converter.NodeWorker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DiscountCardRepositoryImpl extends AbstractRepository implements DiscountRepository {
    @Override
    public DiscountCard findById(long id) {
        List<DiscountCard> nodes = getAll();
        for (DiscountCard product : nodes){
            if (product.getId() == id){
                return product;
            }
        }
        throw new CardNotFoundException(locale.getMessage("error.card_not_found"));
    }

    @Override
    public List<DiscountCard> getAll() {
        Node node;
        NodeWorker<DiscountCard> worker = workerFactory.getDiscountWorker();
        List<DiscountCard> products = new ArrayList<>();
        try {
            node = nodeTreeBuilder.parseXML(env.getProperty("path.card"));
            worker.nodeToList(node, products);
        } catch (ServiceException e) {
            throw new ReadingException(locale.getMessage("error.reading"));
        }
        return products;
    }
}
