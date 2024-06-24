package com.harry.micrometer.controller;

import com.harry.micrometer.model.AccountModel;
import com.harry.micrometer.service.AccountService;
import com.harry.micrometer.service.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService service;

    @PostMapping("/register")
    public ResponseEntity<Void> registerAccount(@RequestBody AccountModel model) {
        log.info("registerAccount :: call method!!");
        this.service.registerAccount(model);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountModel>> getAllAccount() {
        log.info("getAllAccount :: call method!!");
        return new ResponseEntity<>(this.service.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/findByAccountNumber/{accountNumber}")
    public ResponseEntity<AccountModel> getAccountByAccountNumber(@PathVariable String accountNumber) {
        log.info("getAccountByAccountNumber :: call method!!");
        return new ResponseEntity<>(this.service.getAccountByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAccount(AccountModel model) {
        log.info("updateAccount :: call method!!");
        this.service.updateAccount(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/process-transaction")
    public ResponseEntity<String> processTransaction() {
        log.info("processTransaction :: call method!!");
        return new ResponseEntity<>(this.service.processTransaction(), HttpStatus.OK);
    }
}
