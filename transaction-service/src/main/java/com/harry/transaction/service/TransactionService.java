package com.harry.transaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public String processTransaction() {
        log.info("processTransaction :: processing transaction");

        String message = "Transaction processed successfully";
        return message;
    }
}
