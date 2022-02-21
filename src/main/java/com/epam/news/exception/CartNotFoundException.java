package com.epam.news.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CartNotFoundException extends CustomGeneralException {

    public CartNotFoundException(String message) {
        super(message);
    }

}
