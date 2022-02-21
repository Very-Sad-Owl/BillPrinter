package com.project.bill.controller;

import com.project.bill.model.*;
import com.project.bill.service.models_service.CartService;
import com.project.bill.service.models_service.CashierService;
import com.project.bill.service.models_service.DiscountCardService;
import com.project.bill.util.MessageLocaleService;
import com.project.bill.util.args_parser.ArgumentsSorter;
import com.project.bill.util.formatter.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static com.project.bill.util.Constant.*;

@Controller
public class RecepitController extends AbstractController {

    private CartService cartService;
    private CashierService cashierService;
    private DiscountCardService discountCardService;
    private MessageLocaleService locale;
    private Formatter billFormatter;
    private ArgumentsSorter sorter;
    private Logger logger = LoggerFactory.getLogger(RecepitController.class);

    @Autowired
    public void setSorter(ArgumentsSorter sorter){this.sorter = sorter;}

    @Autowired
    @Qualifier("txtFormatter")
    public void setBillFormatter(Formatter billFormatter){this.billFormatter = billFormatter;}

    @Autowired
    public void setCartService(CartService cartService){this.cartService = cartService;}

    @Autowired
    public void setCashierService(CashierService cashierService){this.cashierService = cashierService;}

    @Autowired
    public void setDiscountCardService(DiscountCardService discountCardService){this.discountCardService = discountCardService;}

    @Autowired
    public void setMessageLocaleService(MessageLocaleService locale) {
        this.locale = locale;
    }


    @GetMapping("/error")
    public String getErrorPage() {
        logger.info("go to: " + ERROR_PAGE);
        return ERROR_PAGE;
    }

    @GetMapping("/")
    public ModelAndView main() {
        logger.info("go to: " + INDEX_PAGE);
        return new ModelAndView(INDEX_PAGE);
    }


    @GetMapping("/log")
    public ModelAndView getBillByID(@RequestParam Map<String, String> params) {
        logger.info("action: " + ACTION_FIND_BY_ID);
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        if (params.isEmpty()){
            logger.info("action: " + ACTION_LOG);
            List<Cart> log = cartService.getAll();
            List<String> logStr = billFormatter.formatAll(log);
            modelAndView.addObject(BILL_ATTRIBUTE, logStr);
        } else {
            ParamsDTO dto = sorter.retrieveArgs(params, ACTION_FIND_BY_ID);
            Cart bill = cartService.findById(dto.getBill_id());
            List<String> billStr = billFormatter.format(bill);
            modelAndView.addObject(BILL_ATTRIBUTE, billStr);
        }
        return modelAndView;
    }

    @GetMapping("/print")
    public ModelAndView printBill(@RequestParam Map<String, String> params) {
        logger.info("action: " + ACTION_PRINT);
        logger.info("with params: " + params);
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);

        ParamsDTO dto = sorter.retrieveArgs(params, ACTION_PRINT);
        Cashier cashier = cashierService.findById(dto.getCashier_id());
        DiscountCard discountCard = discountCardService.findById(dto.getCard_id());
        List<Slot> slots = cartService.formSlots(dto.getGoods());

        Cart cart = cartService.formCart(slots, discountCard, cashier);

        List<String> bill = billFormatter.format(cart);
        modelAndView.addObject(BILL_ATTRIBUTE, bill);
        logger.info(bill.toString());

        return modelAndView;
    }

}
