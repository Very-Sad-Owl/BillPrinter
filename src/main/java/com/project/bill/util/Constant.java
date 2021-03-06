package com.project.bill.util;

import org.springframework.stereotype.Component;

@Component
public interface Constant {
    String BASE_PACKAGES_TO_SCAN = "com.project";
    String INDEX_PAGE = "index";
    String ERROR_PAGE = "errorPage";
    String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    String BILL_ATTRIBUTE = "billView";
    String BILL_ID_PARAM = "id";
    String CARD_ID_PARAM = "card_uid";
    String CASHIER_ID_PARAM = "cashier_uid";
    String SAVE_PATH_PARAM = "path";
    String ACTION_PARAM = "action";
    String ACTION_PRINT = "print";
    String ACTION_LOG = "log";
    String ACTION_FIND_BY_ID = "find";

    String CASHIER_XML_NAME = "cashier";
    String CARD_XML_NAME = "card";
    String CART_XML_NAME = "cart";
    String PRODUCT_XML_NAME = "product";
    String CART_COLLECTION_XML_NAME = "carts";
    String SLOTS_COLLECTION_XML_NAME = "slots";
    String SLOT_XML_NAME = "slot";
    String XML_ID_ATTR = "id";
    String XML_NAME_ATTR = "name";
    String XML_BDATE_ATTR = "birthday";
    String XML_SURNAME_ATTR = "surname";
    String XML_DISCOUNT_ATTR = "discount";
    String XML_TITLE_ATTR = "title";
    String XML_PRICE_ATTR = "price";
    String XML_QUANTITY_ATTR = "quantity";

}
