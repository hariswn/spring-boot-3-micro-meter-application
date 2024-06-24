package com.harry.transaction.controller;

import com.harry.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService service;

    @GetMapping("/process")
    public ResponseEntity<String> processTransaction() {
        log.info("processTransaction :: call method!!");
        String status = this.service.processTransaction();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
