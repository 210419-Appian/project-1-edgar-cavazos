package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountId; // primary key
	  private double balance;  // not null
	  private AccountStatus statusAS;
	  private AccountType typeAT;
	  private  int accountOwner;
	  
	  
	  
	public Account() {
		super();		
	}
	
	
	public Account(int accountId, double balance, AccountStatus statusAS, AccountType typeAT, int accountOwner) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.statusAS = statusAS;
		this.typeAT = typeAT;
		this.accountOwner = accountOwner;
	}


	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountStatus getStatusAS() {
		return statusAS;
	}
	public void setStatusAS(AccountStatus statusAS) {
		this.statusAS = statusAS;
	}
	public AccountType getTypeAT() {
		return typeAT;
	}
	public void setTypeAT(AccountType typeAT) {
		this.typeAT = typeAT;
	}
	public int getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(int accountOwner) {
		this.accountOwner = accountOwner;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + accountOwner;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((statusAS == null) ? 0 : statusAS.hashCode());
		result = prime * result + ((typeAT == null) ? 0 : typeAT.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountOwner != other.accountOwner)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (statusAS == null) {
			if (other.statusAS != null)
				return false;
		} else if (!statusAS.equals(other.statusAS))
			return false;
		if (typeAT == null) {
			if (other.typeAT != null)
				return false;
		} else if (!typeAT.equals(other.typeAT))
			return false;
		return true;
	}
	
	

	
	

}
