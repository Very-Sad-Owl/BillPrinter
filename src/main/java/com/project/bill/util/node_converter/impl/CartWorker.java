package com.project.bill.util.node_converter.impl;

import by.epam.training.jwd.task03.entity.Attribute;
import by.epam.training.jwd.task03.entity.Node;
import com.project.bill.model.*;
import com.project.bill.util.node_converter.NodeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.bill.util.Constant.*;

@Service
public class CartWorker extends NodeWorker<Cart> {

    private NodeWorker<Product> productNodeWorker;
    private NodeWorker<Cashier> cashierNodeWorker;
    private NodeWorker<DiscountCard> discountCardNodeWorker;

    @Autowired
    public void setProductNodeWorker(NodeWorker<Product> productNodeWorker){this.productNodeWorker = productNodeWorker;}

    @Autowired
    public void setCashierNodeWorker(NodeWorker<Cashier> cashierNodeWorker){this.cashierNodeWorker = cashierNodeWorker;}

    @Autowired
    public void setDiscountCardNodeWorker(NodeWorker<DiscountCard> discountCardNodeWorker){
        this.discountCardNodeWorker = discountCardNodeWorker;
    }

    @Override
    public Cart nodeToModel(Node node) {
        Cart result = new Cart();
        result.setId(Long.parseLong(node.getAttrByName(XML_ID_ATTR).getContent()));
            for (Node child : node.getChildNodes()){
                switch (child.getName()){
                    case CASHIER_XML_NAME :
                        cashierNodeWorker.nodeToModel(child);
                        result.setCashier(cashierNodeWorker.nodeToModel(child));
                        break;
                    case CARD_XML_NAME:
                        result.setDiscountCard(discountCardNodeWorker.nodeToModel(child));
                        break;
                    case SLOTS_COLLECTION_XML_NAME:
                        for (Node slot : child.getChildNodes()){
                            Slot newSlot = new Slot();
                            newSlot.setQuantity(Integer.parseInt(slot.getAttrByName(XML_QUANTITY_ATTR).getContent()));
                            newSlot.setProduct(productNodeWorker.nodeToModel(slot.getChildNodes().get(0)));
                            result.addSlot(newSlot);
                        }
                        break;
                }
            }
        return result;
    }

    @Override
    public void nodeToList(Node rootNode, List<Cart> models){
        List<Node> nodes = rootNode.getChildNodes();
        for (Node cart : nodes){
            models.add(nodeToModel(cart));
        }
    }

    @Override
    public Node modelToNode(Cart model) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(XML_ID_ATTR, model.getId()+""));
        List<Node> children = new ArrayList<>();
        children.add(cashierNodeWorker.modelToNode(model.getCashier()));
        children.add(discountCardNodeWorker.modelToNode(model.getDiscountCard()));
        children.add(formSlots(model.getPositions()));
        Node cart = Node.newBuilder()
                .withName(CART_XML_NAME)
                .withAttributes(attributes)
                .withChildren(children)
                .build();
        List<Node> cartsChildren = new ArrayList<>();
        cartsChildren.add(cart);
        return Node.newBuilder()
                .withName(CART_COLLECTION_XML_NAME)
                .withChildren(cartsChildren)
                .build();
    }

    private Node formSlots(List<Slot> slots){
        List<Node> nodeSlots = new ArrayList<>();
        for (Slot slot : slots){
            List<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute(XML_QUANTITY_ATTR, slot.getQuantity()+""));
            List<Node> children = new ArrayList<>();
            children.add(productNodeWorker.modelToNode(slot.getProduct()));
            nodeSlots.add(
                    Node.newBuilder()
                    .withName(SLOT_XML_NAME)
                    .withAttributes(attributes)
                    .withChildren(children)
                    .build()
            );
        }
        return Node.newBuilder()
                .withName(SLOTS_COLLECTION_XML_NAME)
                .withChildren(nodeSlots)
                .build();
    }
}
