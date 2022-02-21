package com.epam.news.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CardNotFoundException extends CustomGeneralException {

    public CardNotFoundException(String message) {
        super(message);
    }

}
