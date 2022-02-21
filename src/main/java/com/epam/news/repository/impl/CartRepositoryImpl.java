package com.epam.news.repository.impl;

import by.epam.training.jwd.task03.entity.Node;
import by.epam.training.jwd.task03.service.exception.ServiceException;
import com.epam.news.exception.CartNotFoundException;
import com.epam.news.exception.ReadingException;
import com.epam.news.exception.WritingException;
import com.epam.news.model.Cart;
import com.epam.news.repository.models_repo.CartRepository;
import com.epam.news.util.formatter.Formatter;
import com.epam.news.util.node_converter.NodeWorker;
import com.epam.news.util.node_converter.impl.CartWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl extends AbstractRepository implements CartRepository {

    private Formatter cartFormatter;

    @Autowired
    public void setCartFormatter(Formatter cartFormatter){this.cartFormatter = cartFormatter;}

    @Override
    public void save(Cart cart) {
        NodeWorker<Cart> worker = workerFactory.getCartWorker();
        Node cartNode = worker.modelToNode(cart);
        try {
            boolean isAppend = !nodeTreeBuilder.checkFileEmpty(env.getProperty("path.bill_log_absolute"));
            if (isAppend){
                cartNode = cartNode.getChildNodes().get(0);
            }
            nodeTreeBuilder.writeXML(env.getProperty("path.bill_log_absolute"),
                    cartNode, isAppend);
        } catch (ServiceException e) {
            throw new WritingException(locale.getMessage("error.writing"));
        }
    }


    @Override
    public Cart findById(long id){
        List<Cart> carts = getAll();
        for (Cart cart : carts){
            if (cart.getId() == id){
                return cart;
            }
        }
        throw new CartNotFoundException(locale.getMessage("error.bill_not_found"));
    }

    @Override
    public List<Cart> getAll(){
        Node node;
        CartWorker worker = (CartWorker) workerFactory.getCartWorker();
        List<Cart> log = new ArrayList<>();
        try {
            node = nodeTreeBuilder.parseXML(env.getProperty("path.bill_log"));
            worker.nodeToList(node, log);
        } catch (ServiceException e) {
            throw new ReadingException(locale.getMessage("error.reading"));
        }
        return log;
    }

    @Override
    public void printBill(Cart cart, String path) {
        File file = new File(path);
        try (
                FileWriter fwriter = new FileWriter(file, false);
                BufferedWriter bfwriter = new BufferedWriter(fwriter)
             ){
            List<String> content = cartFormatter.format(cart);

            for (String line : content){
                bfwriter.write(line);
                bfwriter.newLine();
            }
            bfwriter.flush();
        } catch (IOException e) {
            throw new WritingException(locale.getMessage("error.writing"));
        }
    }
}
