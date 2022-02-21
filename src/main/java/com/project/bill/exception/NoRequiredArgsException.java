package com.project.bill.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NoRequiredArgsException extends CustomGeneralException {

    public NoRequiredArgsException(String message) {
        super(message);
    }

}
