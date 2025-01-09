package com.project.ATM_Interface.com.Controller;
import com.project.ATM_Interface.com.Model.Account;
import com.project.ATM_Interface.com.dto.TransactionRequest;
import com.project.ATM_Interface.com.Service.ATMService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/atm")
@RequiredArgsConstructor

public class ATMController {
	
	private final ATMService atmService;
	public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }
	@PostMapping("/api/atm/create-account")
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
	    atmService.createAccount(account);
	    return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully!");
	}


    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber, @RequestParam String pin) {
        return ResponseEntity.ok(atmService.getBalance(accountNumber, pin));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(atmService.withdraw(request));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(atmService.deposit(request));
    }

}
