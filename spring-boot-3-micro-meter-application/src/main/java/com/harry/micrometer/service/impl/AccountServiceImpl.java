package com.harry.micrometer.service.impl;

import com.harry.micrometer.exception.AccountNotFoundException;
import com.harry.micrometer.exception.NoDataFoundException;
import com.harry.micrometer.model.AccountModel;
import com.harry.micrometer.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final Map<String, AccountModel> dataStore = new HashMap<>();


    @Override
    public void registerAccount(AccountModel model) {
        log.info("registerAccount :: register new account, account number - {}", model.getNumber());
        dataStore.put(model.getNumber(), model);
    }

    @Override
    public List<AccountModel> getAllAccount() {
        log.info("getAllAccount :: read all account");
        Collection<AccountModel> data = dataStore.values();
        if(data == null || data.size() == 0) {
            throw new NoDataFoundException("getAllAccount :: no data found in DB");
        }
        return (List<AccountModel>) data;
    }

    @Override
    public AccountModel getAccountByAccountNumber(String accountNumber) {
        log.info("getAccountByAccountNumber :: read account information for the account number - {}", accountNumber);
        AccountModel accountModel = dataStore.get(accountNumber);
        if(accountModel == null) {
            log.info("getAccountByAccountNumber :: account not found with account number - {}", accountNumber);
            throw new AccountNotFoundException("account not found with account number " + accountNumber);
        }
        return accountModel;
    }

    @Override
    public void updateAccount(AccountModel model) {
        log.info("updateAccount :: update account, account - {}", model);
        if (dataStore.containsKey(model.getNumber())) {
            throw new AccountNotFoundException("account not found for the account update with account number " + model.getNumber());
        }

        dataStore.put(model.getNumber(), model);
    }

    @Override
    public String processTransaction() {
        log.info("processTransaction :: call to process the transaction");
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://localhost:9292/transactions/process", String.class);

        return responseEntity.getBody();
    }
}
