package com.project.ATM_Interface.com.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequest {
	private String accountNumber;
    public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	private String pin;
    private BigDecimal amount;
}
