package com.project.bill.util.formatter;

import com.project.bill.model.Cart;
import com.project.bill.model.Cashier;
import com.project.bill.model.Slot;
import com.project.bill.util.MessageLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("txtFormatter")
public class BillPseudographicFormatter extends Formatter {

    private MessageLocaleService locale;

    @Autowired
    public void setMessageLocaleService(MessageLocaleService locale) {
        this.locale = locale;
    }

    private final int SYMBOL_LIMIT_PER_LINE = 48;

    @Override
    public List<String> format(Cart cart) {
        List<String> billBuilder = new ArrayList<>();
        drawMetaInfo(cart.getId(), billBuilder);
        drawCashierInfo(cart.getCashier(), billBuilder);
        for (Slot slot : cart.getPositions()){
            drawSlotInfo(slot, billBuilder);
        }
        drawPaymentInfo(cart, billBuilder);

        return billBuilder;
    }

    @Override
    public List<String> formatAll(List<Cart> cart) {
        List<String> billBuilder = new ArrayList<>();
        for (Cart bill : cart){
            List<String> billList = format(bill);
            billBuilder = Stream.concat(billBuilder.stream(), billList.stream())
                    .collect(Collectors.toList());
        }
        return billBuilder;
    }

    @Override
    public String drawLine(char delimiter, int len) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < len; i++){
            line.append(delimiter);
        }
        return line.toString();
    }

    @Override
    public String centreLine(String line, char delimiter) {
        StringBuilder offsetLine = new StringBuilder();
        int offset = (SYMBOL_LIMIT_PER_LINE - line.length()) / 2;
        offsetLine.append(drawLine(delimiter, offset));
        offsetLine.append(line);
        offsetLine.append(drawLine(delimiter, offset));
        return offsetLine.toString();
    }

    @Override
    public void drawMetaInfo(long cartId, List<String> billBuilder) {
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_char").charAt(0), SYMBOL_LIMIT_PER_LINE));
        billBuilder.add(centreLine(locale.getMessage("info.shop_title"), ' '));
        billBuilder.add(centreLine(locale.getMessage("info.address"), ' '));
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_char").charAt(0), SYMBOL_LIMIT_PER_LINE));
        billBuilder.add(centreLine(
                locale.getMessage("label.receipt_uid") + " " + cartId, ' '));
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_delimiter").charAt(0), SYMBOL_LIMIT_PER_LINE));
    }

    @Override
    public void drawCashierInfo(Cashier cashier, List<String> billBuilder) {
        billBuilder.add(
                locale.getMessage("label.cashier_uid") +
                        locale.getMessage("label.definition") +
                        cashier.getId()
        );
        billBuilder.add(
                locale.getMessage("label.cashier") +
                        locale.getMessage("label.definition") +
                            cashier.getFullName()
        );
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_delimiter").charAt(0),
                SYMBOL_LIMIT_PER_LINE));

    }

    @Override
    public void drawSlotInfo(Slot slot, List<String>billBuilder) {
        billBuilder.add(
                slot.getProduct().getId() + " " +
                        slot.getProduct().getTitle() + " " +
                        slot.getQuantity()

        );
        billBuilder.add(
                locale.getMessage("label.original_price") +
                        locale.getMessage("label.definition") +
                        slot.getTotalPrice() + " " +
                        locale.getMessage("label.total_price") +
                        locale.getMessage("label.definition") +
                        slot.getRawPrice()
        );
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_delimiter").charAt(0),
                SYMBOL_LIMIT_PER_LINE));
    }

    @Override
    public void drawPaymentInfo(Cart cart, List<String> billBuilder) {
        billBuilder.add(locale.getMessage("label.discount_card_uid") + cart.getDiscountCard().getId());
        billBuilder.add(
                locale.getMessage("label.original_price") +
                        locale.getMessage("label.definition") +
                        cart.getRawPrice()
        );
        billBuilder.add(
                locale.getMessage("label.tottal_discount") +
                        locale.getMessage("label.definition") +
                        cart.getTotalDiscount() +
                        locale.getMessage("label.discount_percentage")
        );
        billBuilder.add(
                locale.getMessage("label.total_price") +
                        locale.getMessage("label.definition") +
                        cart.getPrice()
        );
        billBuilder.add(drawLine(locale.getMessage("label.pseudographics_delimiter").charAt(0),
                SYMBOL_LIMIT_PER_LINE));

    }
}
