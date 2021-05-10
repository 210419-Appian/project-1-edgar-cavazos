package com.revature.models;

public class WithdrawDTO {
	
	public int accountId;
	public double amount;
	
	public WithdrawDTO() {
		super();
	}

	public WithdrawDTO(int accountId, double withdrawAmount) {
		super();
		this.accountId = accountId;
		this.amount = withdrawAmount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
	
	
	
	

}
