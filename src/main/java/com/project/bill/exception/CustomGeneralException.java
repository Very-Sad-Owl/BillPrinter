package com.project.bill.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomGeneralException extends RuntimeException {

    public CustomGeneralException(String message) {
        super(message);
    }

    public CustomGeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}
