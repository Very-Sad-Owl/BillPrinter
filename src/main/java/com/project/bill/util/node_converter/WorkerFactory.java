package com.project.bill.util.node_converter;

import com.project.bill.model.Cart;
import com.project.bill.model.Cashier;
import com.project.bill.model.DiscountCard;
import com.project.bill.model.Product;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WorkerFactory {

    private static final WorkerFactory instance = new WorkerFactory();
    private NodeWorker<Product> productWorker;
    private NodeWorker<DiscountCard> discountWorker;
    private NodeWorker<Cashier> cashierWorker;
    private NodeWorker<Cart> cartWorker;

    @Autowired
    public void setProductWorker(NodeWorker<Product> productWorker){this.productWorker = productWorker;}

    @Autowired
    public void setDiscountWorker(NodeWorker<DiscountCard> discountWorker){this.discountWorker = discountWorker;}

    @Autowired
    public void setCashierWorker(NodeWorker<Cashier> cashierWorker){this.cashierWorker = cashierWorker;}

    @Autowired
    public void setCartWorker(NodeWorker<Cart> cartWorker){this.cartWorker = cartWorker;}

    private WorkerFactory(){}


    public static WorkerFactory getInstance() {
        return instance;
    }
}
