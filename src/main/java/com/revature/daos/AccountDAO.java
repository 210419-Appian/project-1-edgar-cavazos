package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	
	public List<Account> findAll();
	public boolean add(Account a);
	public Account findById(int id);
	public Account findByStatus(int status);
	public Account findByType(int type);
	public Account findByOwner(int owner);
	public boolean updateAccount(Account a);
	public boolean deleteAccount(int id);
	public boolean depositTrans(double amount, int accountId);
	public boolean withdrawTrans(double amount, int accountId);
	boolean transferTrans(int sourceAccountId, int targetAccountId, double amount);
	public List<Account> findAllByStatus(int statusId);
	public List<Account> findAllByOwner(int statusId);
	
	

}
