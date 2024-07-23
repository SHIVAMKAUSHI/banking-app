package net.javaguides.banking_app.service.impl;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.mapper.AccountMapper;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exitst"));
        return AccountMapper.mapperAccounttoAccountDto(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapperAccountDtotoAccount(accountDto);
        accountRepository.save(account);
        return AccountMapper.mapperAccounttoAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not Exits"));
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
        return AccountMapper.mapperAccounttoAccountDto(account);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Does not Esist"));
        if(account.getBalance()>=amount) {
            account.setBalance(account.getBalance() - amount);
        }
        else {
            throw new RuntimeException("Insufficient Balance");
        }
        accountRepository.save(account);
        return AccountMapper.mapperAccounttoAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> all = accountRepository.findAll();
        List<AccountDto> allAccountDto=new ArrayList<AccountDto>();
        for (Account account:all)
        {
                allAccountDto.add(AccountMapper.mapperAccounttoAccountDto(account));
        }
        return allAccountDto;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Accont is not present"));
        accountRepository.delete(account);
    }
}
