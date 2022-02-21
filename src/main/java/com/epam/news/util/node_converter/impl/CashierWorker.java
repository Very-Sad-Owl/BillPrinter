package com.epam.news.util.node_converter.impl;

import by.epam.training.jwd.task03.entity.Attribute;
import by.epam.training.jwd.task03.entity.Node;
import com.epam.news.model.Cashier;
import com.epam.news.util.node_converter.NodeWorker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.epam.news.util.Constant.*;

@Service
public class CashierWorker extends NodeWorker<Cashier> {
    @Override
    public Cashier nodeToModel(Node node) {
        return new Cashier(
                Long.parseLong(node.getAttrByName(XML_ID_ATTR).getContent()),
                node.getAttrByName(XML_NAME_ATTR).getContent(),
                node.getAttrByName(XML_SURNAME_ATTR).getContent()
        );
    }

    @Override
    public Node modelToNode(Cashier model) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(XML_ID_ATTR, model.getId()+""));
        attributes.add(new Attribute(XML_NAME_ATTR, model.getName()));
        attributes.add(new Attribute(XML_SURNAME_ATTR, model.getSurname()));
        return Node.newBuilder()
                .withName(CASHIER_XML_NAME)
                .withAttributes(attributes)
                .build();
    }
}
