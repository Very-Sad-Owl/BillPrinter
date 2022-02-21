package com.epam.news.util.args_parser;

import com.epam.news.exception.NoRequiredArgsException;
import com.epam.news.model.ParamsDTO;
import com.epam.news.util.MessageLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.news.util.Constant.*;

@Service
public class ArgumentsSorter {

    private MessageLocaleService messageLocaleService;

    @Autowired
    public void setMessageLocaleService(MessageLocaleService messageLocaleService){this.messageLocaleService = messageLocaleService;}

    public ParamsDTO retrieveArgs(Map<String, String> args, String action){
        ParamsDTO dto = new ParamsDTO();
        for (Map.Entry<String, String> pair : args.entrySet()) {
            switch (pair.getKey()){
                case CARD_ID_PARAM:
                    dto.setCard_id(Long.parseLong(pair.getValue()));
                    break;
                case BILL_ID_PARAM:
                    dto.setBill_id(Long.parseLong(pair.getValue()));
                    break;
                case CASHIER_ID_PARAM:
                    dto.setCashier_id(Long.parseLong(pair.getValue()));
                    break;
                case SAVE_PATH_PARAM:
                    dto.setDestinationPath(pair.getValue());
                    break;
                default:
                    long productId = Long.parseLong(pair.getKey().split("-")[0]);
                    int quantity = Integer.parseInt(pair.getKey().split("-")[1]);
                    dto.getGoods().put(productId, quantity);
            }
        }
        if (!checkRequiredArgs(args, action)) {
            throw new NoRequiredArgsException(messageLocaleService.getMessage("error.no_req_args"));
        }
        return dto;
    }

    public ParamsDTO retrieveArgs(String[] args){
        ParamsDTO params = new ParamsDTO();
        String[] pair;
        Map<Long, Integer> goods = new HashMap<>();
        for (String arg : args){
            pair = arg.split("-");
            switch (pair[0]) {
                case CARD_ID_PARAM:
                    params.setCard_id(Long.parseLong(pair[1]));
                    break;
                case CASHIER_ID_PARAM:
                    params.setCashier_id(Long.parseLong(pair[1]));
                    break;
                case SAVE_PATH_PARAM:
                    params.setDestinationPath(pair[1]);
                    break;
                case ACTION_PARAM:
                    params.setAction(pair[1]);
                    break;
                case BILL_ID_PARAM:
                    params.setBill_id(Long.parseLong(pair[1]));
                    break;
                default:
                    goods.put(Long.parseLong(pair[0]), Integer.parseInt(pair[1]));
                    break;
            }
        }
        params.setGoods(goods);
        if (params.getAction() != null && !checkRequiredArgs(args, params.getAction())){
            throw new NoRequiredArgsException(messageLocaleService.getMessage("error.no_req_args"));
        }
        return params;
    }

    private boolean checkRequiredArgs(Map<String, String> args, String action) {
        List<String> required = formRequiredArgs(action);
        if (required.isEmpty()) return true;
        boolean flag = false;
        for (String key : required) {
            for (Map.Entry<String, String> pair : args.entrySet()) {
                flag = key.equals(pair.getKey());
                if (flag) break;
            }
            if (!flag) return false;
        }
        return flag;
    }

    private boolean checkRequiredArgs(String[] args, String action) {
        List<String> required = formCmdRequiredArgs(action);
        if (required.isEmpty()) return true;
        boolean flag = false;
        for (String key : required) {
            for (String arg : args) {
                flag = key.equals(arg.split("-")[0]);
                if (flag) break;
            }
            if (!flag) return false;
        }
        return flag;
    }

    private List<String> formRequiredArgs(String action){
        List<String> required = new ArrayList<>();
        if (ACTION_PRINT.equals(action)) {
            required.add(CARD_ID_PARAM);
            required.add(CASHIER_ID_PARAM);
        }
        return required;
    }

    private List<String> formCmdRequiredArgs(String action){
        List<String> required = new ArrayList<>();
        switch (action){
            case ACTION_PRINT:
                required.add(CARD_ID_PARAM);
                required.add(CASHIER_ID_PARAM);
                required.add(ACTION_PARAM);
                break;
            case ACTION_FIND_BY_ID:
                required.add(BILL_ID_PARAM);
                break;
        }
        return required;
    }

}
