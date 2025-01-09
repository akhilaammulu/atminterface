package com.project.ATM_Interface.com.Service;


import com.project.ATM_Interface.com.Model.Account;
import com.project.ATM_Interface.com.dto.TransactionRequest;
import com.project.ATM_Interface.com.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class ATMService {

	
	
	private final AccountRepository accountRepository;
	public ATMService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	public void createAccount(Account account) {
	    // Validate account data (e.g., check for null fields)
	    if (account.getAccountNumber() == null || account.getPin() == null) {
	        throw new IllegalArgumentException("Account number and PIN are required!");
	    }
	    accountRepository.save(account); // Save the account to the database
	}


    public BigDecimal getBalance(String accountNumber, String pin) {
        Account account = validateAccount(accountNumber, pin);
        return account.getBalance();
    }

    public Account withdraw(TransactionRequest request) {
        Account account = validateAccount(request.getAccountNumber(), request.getPin());
        
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        
        account.setBalance(account.getBalance().subtract(request.getAmount()));
        return accountRepository.save(account);
    }

    public Account deposit(TransactionRequest request) {
        Account account = validateAccount(request.getAccountNumber(), request.getPin());
        account.setBalance(account.getBalance().add(request.getAmount()));
        return accountRepository.save(account);
    }

    private Account validateAccount(String accountNumber, String pin) {
        return accountRepository.findByAccountNumber(accountNumber)
                .filter(account -> account.getPin().equals(pin))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid account or PIN"));
    }
}
