package com.project.bill.repository.impl;

import by.epam.training.jwd.task03.entity.Node;
import by.epam.training.jwd.task03.service.exception.ServiceException;
import com.project.bill.exception.ProductNotFoundException;
import com.project.bill.exception.ReadingException;
import com.project.bill.model.Product;
import com.project.bill.repository.models_repo.ProductRepository;
import com.project.bill.util.node_converter.NodeWorker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl extends AbstractRepository implements ProductRepository {

    @Override
    public Product findById(long id) {
        List<Product> nodes = getAll();
        for (Product product : nodes){
            if (product.getId() == id){
                return product;
            }
        }
        throw new ProductNotFoundException(locale.getMessage("error.product_not_found"));
    }

    @Override
    public List<Product> getAll() {
        Node node;
        NodeWorker<Product> worker = workerFactory.getProductWorker();
        List<Product> products = new ArrayList<>();
        try {
            node = nodeTreeBuilder.parseXML(env.getProperty("path.product"));
            worker.nodeToList(node, products);
        } catch (ServiceException e) {
            throw new ReadingException(locale.getMessage("error.reading"));
        }
        return products;
    }


}
