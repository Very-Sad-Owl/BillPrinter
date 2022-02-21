package com.epam.news;

import com.epam.news.controller.RecepitController;
import com.epam.news.exception.CustomGeneralException;
import com.epam.news.model.*;
import com.epam.news.service.models_service.CartService;
import com.epam.news.service.models_service.CashierService;
import com.epam.news.service.models_service.DiscountCardService;
import com.epam.news.util.MessageLocaleService;
import com.epam.news.util.args_parser.ArgumentsSorter;
import com.epam.news.util.formatter.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static com.epam.news.util.Constant.*;

@SpringBootApplication(scanBasePackages = BASE_PACKAGES_TO_SCAN)
@Profile("!test")
public class NewsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	private CartService cartService;
	private CashierService cashierService;
	private DiscountCardService discountCardService;
	private MessageLocaleService locale;
	private Formatter billFormatter;
	private ArgumentsSorter sorter;
	private Logger logger = LoggerFactory.getLogger(NewsApplication.class);

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


	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println(locale.getMessage("label.guide"));
			ParamsDTO params = sorter.retrieveArgs(args);
			logger.info("action:" + params.getAction());
			switch (params.getAction()) {
				case ACTION_PRINT:
					Cashier cashier = cashierService.findById(params.getCashier_id());
					DiscountCard discountCard = discountCardService.findById(params.getCard_id());
					List<Slot> slots = cartService.formSlots(params.getGoods());
					Cart cart = cartService.formCart(slots, discountCard, cashier);
					List<String> bill = billFormatter.format(cart);
					for (String line : bill){
						System.out.println(line);
					}
					break;
				case ACTION_LOG:
					List<Cart> log = cartService.getAll();
					List<String> logStr = billFormatter.formatAll(log);
					for (String line : logStr){
						System.out.println(line);
					}
					break;
				case ACTION_FIND_BY_ID:
					Cart found = cartService.findById(params.getBill_id());
					List<String> billStr = billFormatter.format(found);
					for (String line : billStr){
						System.out.println(line);
					}
					break;
			}
		} catch (CustomGeneralException e){
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
	}
}

//1-7 2-3 card_uid-1 cashier_uid-2 action-print