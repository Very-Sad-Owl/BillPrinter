package com.project.bill.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReadingException extends CustomGeneralException {

    public ReadingException(String message) {
        super(message);
    }

}
