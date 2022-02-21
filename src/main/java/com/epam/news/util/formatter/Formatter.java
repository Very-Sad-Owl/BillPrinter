package com.epam.news.util.formatter;

import com.epam.news.model.Cart;
import com.epam.news.model.Cashier;
import com.epam.news.model.Slot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class Formatter {

    public abstract List<String> format(Cart cart);
    public abstract List<String> formatAll(List<Cart> cart);
    protected abstract void drawMetaInfo(long cartId, List<String> destination);
    protected abstract void drawCashierInfo(Cashier cashier, List<String> destination);
    protected abstract void drawSlotInfo(Slot slot, List<String> destination);
    protected abstract void drawPaymentInfo(Cart cart, List<String> destination);
    protected abstract String drawLine(char delimiter, int len);
    protected abstract String centreLine(String line, char delimiter);
}
