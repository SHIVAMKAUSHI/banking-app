package net.javaguides.banking_app.controller;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto ){
        AccountDto accountDto2 = accountService.createAccount(accountDto);

        return new ResponseEntity<>(accountDto2, HttpStatus.CREATED);
    }
    @GetMapping("/getAccount/{id}")
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
//        return new ResponseEntity<>(accountDto,HttpStatus.FOUND);
        return  ResponseEntity.ok(accountDto);
    }
    @PutMapping("/depositAmount/{id}")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDto deposit = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(deposit);

    }
    @PutMapping("withdrawAmount/{id}")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double withdraw = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, withdraw);
        return  ResponseEntity.ok(accountDto);
    }
    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    @DeleteMapping("deletAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){

        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully...");
    }

}
