package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {
	private AccountDAO aDao = new AccountDAOImpl();
	
	public List<Account> findAllAccounts(){
		return aDao.findAll();
	}
	
	public boolean addAccount(Account a) {
		return aDao.add(a);
	}
	
	public Account findAccountById(int id) {
		return aDao.findById(id);
	}
	
	public Account findAccountByStatus(int status) {
		return aDao.findByStatus(status);
	}
	
	public Account findAccountByType(int type) {
		return aDao.findByType(type);
	}
	
	public Account findAccountByUser(int owner) {
		return aDao.findByOwner(owner);
	}
	
	public boolean updateFullAccount(Account a){
		return aDao.updateAccount(a);
	}
	
	public boolean updatePartialAccount(Account a) {
		
		if(a.getAccountOwner()==0) {
			return false;
		}
		
		Account acct = new Account();
	
		if(a.getStatusAS() == null) {
			a.setStatusAS(acct.getStatusAS());
		}
		if(a.getTypeAT() == null) {
			a.setTypeAT(acct.getTypeAT());
		}
		
		return aDao.updateAccount(a);
	}
	
	public boolean deleteSingleAccount(int id) {
		return aDao.deleteAccount(id);
	}
	
	public boolean depositTransaction(double amount, int accountId) {
		return aDao.depositTrans(amount, accountId);
	}
		
	public boolean withdrawTransaction(double amount, int accountId) {
		return aDao.withdrawTrans(amount, accountId);
	}
	
	public boolean transferTransaction(int sourceAccountId, int targetAccountId, double amount) {
		return aDao.transferTrans(sourceAccountId, targetAccountId,amount);
	}
	
	public List<Account> findAllAccountsByStatus(int statusId){
		return aDao.findAllByStatus(statusId);
	}
	
	public List<Account> findAllAccountsByOwnerId(int statusId){
		return aDao.findAllByOwner(statusId);
	}

}
