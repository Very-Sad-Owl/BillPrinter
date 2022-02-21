package com.epam.news.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProductNotFoundException extends CustomGeneralException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
