package com.epam.news.util.args_parser;

import com.epam.news.config.AppConfig;
import com.epam.news.config.WebConfig;
import com.epam.news.exception.NoRequiredArgsException;
import com.epam.news.model.ParamsDTO;
import com.epam.news.util.MessageLocaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static com.epam.news.util.Constant.*;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {ArgumentsSorter.class, WebConfig.class, AppConfig.class, MessageLocaleService.class})
class ArgumentsSorterTest {

    private ArgumentsSorter sorter;
    private MessageLocaleService locale;

    @Autowired
    public void setSorter(ArgumentsSorter sorter){this.sorter = sorter;}

    @Autowired
    public void setMessageLocaleService(MessageLocaleService locale) {
        this.locale = locale;
    }


    @Test
    void retrieveArgs_correctCmdArgsForPrint_success() {
        ParamsDTO expected = new ParamsDTO();
        expected.setAction(ACTION_PRINT);
        expected.setCard_id(1L);
        expected.setCashier_id(2L);
        Map<Long, Integer> goods = new HashMap<>();
        goods.put(1L, 7);
        goods.put(2L, 3);
        expected.setGoods(goods);

        String[] params = new String[]
                {"1-7", "2-3", "card_uid-1", "cashier_uid-2", "action-print"};
        ParamsDTO actual = sorter.retrieveArgs(params);

        assertEquals(expected, actual);

    }

    @Test
    void retrieveArgs_correctCmdArgsForFind_success() {
        ParamsDTO expected = new ParamsDTO();
        expected.setAction(ACTION_FIND_BY_ID);
        expected.setBill_id(1L);

        String[] params = new String[]
                {"id-1", "action-find"};
        ParamsDTO actual = sorter.retrieveArgs(params);

        assertEquals(expected, actual);
    }

    @Test
    void retrieveArgs_correctCmdArgsForLog_success() {
        ParamsDTO expected = new ParamsDTO();
        expected.setAction(ACTION_LOG);

        String[] params = new String[]
                {"action-log"};
        ParamsDTO actual = sorter.retrieveArgs(params);

        assertEquals(expected, actual);
    }

    @Test
    void retrieveArgs_incorrectCmdArgsForPrint_exception() {
        String[] params = new String[]
                {"1-7", "2-3", "cashier_uid-2", "action-print"};

        Exception exception = assertThrows(NoRequiredArgsException.class, () -> {
            sorter.retrieveArgs(params);
        });

        String expectedMessage = locale.getMessage("error.no_req_args");
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void retrieveArgs_incorrectCmdArgsForFind_exception() {
        String[] params = new String[]
                {"action-find"};

        Exception exception = assertThrows(NoRequiredArgsException.class, () -> {
            sorter.retrieveArgs(params);
        });

        String expectedMessage = locale.getMessage("error.no_req_args");
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}