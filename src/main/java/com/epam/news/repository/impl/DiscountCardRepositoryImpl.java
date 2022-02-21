package com.epam.news.repository.impl;

import by.epam.training.jwd.task03.entity.Node;
import by.epam.training.jwd.task03.service.exception.ServiceException;
import com.epam.news.exception.CardNotFoundException;
import com.epam.news.exception.ReadingException;
import com.epam.news.model.DiscountCard;
import com.epam.news.repository.models_repo.DiscountRepository;
import com.epam.news.util.node_converter.NodeWorker;
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
