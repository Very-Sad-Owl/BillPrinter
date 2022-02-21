package com.epam.news.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NoRequiredArgsException extends CustomGeneralException {

    public NoRequiredArgsException(String message) {
        super(message);
    }

}
