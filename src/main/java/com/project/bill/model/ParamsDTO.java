package com.project.bill.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ParamsDTO {
    private Map<Long, Integer> goods = new HashMap<>();
    private long cashier_id;
    private long card_id;
    private long bill_id;
    private String destinationPath;
    private String action;
}
