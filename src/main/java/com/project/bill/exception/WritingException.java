package com.project.bill.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class WritingException extends CustomGeneralException {

    public WritingException(String message) {
        super(message);
    }

}
