package com.project.bill.util.node_converter.impl;

import by.epam.training.jwd.task03.entity.Attribute;
import by.epam.training.jwd.task03.entity.Node;
import com.project.bill.model.Product;
import com.project.bill.model.ProductDiscountType;
import com.project.bill.util.node_converter.NodeWorker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.bill.util.Constant.*;

@Service
public class ProductWorker extends NodeWorker<Product> {

    @Override
    public Product nodeToModel(Node node) {
        return new Product(
                Long.parseLong(node.getAttrByName(XML_ID_ATTR).getContent()),
                node.getAttrByName(XML_TITLE_ATTR).getContent(),
                Double.parseDouble(node.getAttrByName(XML_PRICE_ATTR).getContent()),
                ProductDiscountType.valueOf(node.getAttrByName(XML_DISCOUNT_ATTR).getContent().toUpperCase())
        );
    }

    @Override
    public Node modelToNode(Product model) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(XML_ID_ATTR, model.getId()+""));
        attributes.add(new Attribute(XML_TITLE_ATTR, model.getTitle()));
        attributes.add(new Attribute(XML_PRICE_ATTR, model.getPrice()+""));
        attributes.add(new Attribute(XML_DISCOUNT_ATTR, model.getDiscountType().toString().toLowerCase()));
        return Node.newBuilder()
                .withName(PRODUCT_XML_NAME)
                .withAttributes(attributes)
                .build();
    }

}
