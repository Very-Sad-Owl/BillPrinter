package com.project.bill.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CashierNotFoundException extends CustomGeneralException {

    public CashierNotFoundException(String message) {
        super(message);
    }

}
