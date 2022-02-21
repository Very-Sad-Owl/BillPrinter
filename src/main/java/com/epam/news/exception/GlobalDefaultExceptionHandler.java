package com.epam.news.exception;

import com.epam.news.controller.RecepitController;
import com.epam.news.util.MessageLocaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.epam.news.util.Constant.*;

@ControllerAdvice(BASE_PACKAGES_TO_SCAN)
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    private MessageLocaleService locale;

    @Autowired
    public void setMessageLocaleService(MessageLocaleService locale) {
        this.locale = locale;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerGlobal(Exception e) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        logger.info(locale.getMessage("error.infoException") + e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(CustomGeneralException.class)
    public ModelAndView handlerCustom(CustomGeneralException e){
       return handlerGlobal(e);
    }

}
