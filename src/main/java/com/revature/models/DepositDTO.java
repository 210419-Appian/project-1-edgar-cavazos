package com.revature.models;

public class DepositDTO {
	
	public int accountId;
	public double amount;
	
	public DepositDTO() {
		super();
	}

	public DepositDTO(int accountId, double amount) {
		super();
		this.accountId = accountId;
		this.amount = amount;
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
