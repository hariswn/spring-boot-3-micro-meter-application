package com.harry.micrometer.service;

import com.harry.micrometer.model.AccountModel;

import java.util.List;

public interface AccountService {

    void registerAccount(AccountModel model);
    List<AccountModel> getAllAccount();
    AccountModel getAccountByAccountNumber(String accountNumber);
    void updateAccount(AccountModel model);

    String processTransaction();
}
