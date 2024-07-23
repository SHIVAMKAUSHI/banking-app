package net.javaguides.banking_app.service;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void deleteAccount(Long id);

}
