package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{
	
	private static AccountStatusDAO asDAO = new AccountStatusDAOImpl();
	private static AccountTypeDAO atDAO = new AccountTypeDAOImpl();

	@Override
	public List<Account> findAll() {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account acct = new Account();
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
				
				list.add(acct);
			}
				return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean add(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account (balance, as_status, at_type, acount_owner_id)"
					+ "VALUES(?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
				
			int index = 0;
			statement.setDouble(++index, a.getBalance());	
			if(a.getStatusAS() != null) {
				statement.setString(++index, a.getStatusAS().getStatus());
			}else {
				statement.setString(++index, null);
			}
			if(a.getTypeAT() != null) {
				statement.setString(++index, a.getTypeAT().getType());
			}else {
				statement.setString(++index, null);
			}
			statement.setInt(++index, a.getAccountOwner());
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account findById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account WHERE account_id = "+id+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Account acct = new Account();
			
			while(result.next()) {
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
			}
			
			return acct;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByStatus(int status) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account WHERE as_status = "+status+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Account acct = new Account();
			
			while(result.next()) {
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
			}
			
			return acct;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Account> findAllByStatus(int statusId) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE as_status = "+statusId+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account acct = new Account();
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
				
				list.add(acct);
			}
				return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account findByType(int type) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account WHERE at_type = "+type+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Account acct = new Account();
			
			while(result.next()) {
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
			}
			
			return acct;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByOwner(int owner) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_owner_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, owner);
			ResultSet result = statement.executeQuery();
			
			Account acct = new Account();
			
			while(result.next()) {
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getDouble("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));
				acct.setAccountOwner(result.getInt("account_owner_id"));				
			}
		

		return acct;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public boolean updateAccount(Account a) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE account SET balance = ?, as_status = ?, at_type = ?, account_owner_id = ? WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);			
					

			int index = 0;
			statement.setDouble(++index, a.getBalance());	
			if(a.getStatusAS() != null) {
				statement.setInt(++index, a.getStatusAS().getStatusId());
			}else {
				statement.setInt(++index, java.sql.Types.INTEGER);
			}
			if(a.getTypeAT() != null) {
				statement.setInt(++index, a.getTypeAT().getTypeId());
			}else {
				statement.setNull(++index, java.sql.Types.INTEGER);
			}
			statement.setInt(++index, a.getAccountOwner());
			statement.setInt(++index, a.getAccountId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		}

	@Override
	public boolean deleteAccount(int id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM account WHERE account_id = " + id + ";";
			Statement statement = conn.createStatement();
			statement.execute(sql);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}		
	
	
	@Override
	public boolean transferTrans(int sourceAccountId, int targetAccountId, double amount) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "BEGIN;"
						+"UPDATE account SET balance = balance - ? WHERE account_id = ?;"
						+"UPDATE account SET balance = balance + ? WHERE account_id = ?;"
						+"COMMIT;";
			PreparedStatement statement = conn.prepareStatement(sql);			
			
			statement.setDouble(1, amount);
			statement.setInt(2, sourceAccountId);
			statement.setDouble(3, amount);
			statement.setInt(4,targetAccountId);
	
			statement.execute();
		

		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
	
	
	@Override
	public boolean withdrawTrans(double amount, int accountId) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET balance = balance - ?"
					+ "WHERE account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);			
			
			statement.setDouble(1, amount);
			statement.setInt(2, accountId);
	
			statement.execute();
		

		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
	
	@Override
	public boolean depositTrans(double amount, int accountId) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET balance = balance + ?"
						+"WHERE account_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);			
			
			
			statement.setDouble(1, amount);
			statement.setInt(2, accountId);
	
			statement.execute();
		

		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}

	@Override
	public List<Account> findAllByOwner(int ownerId) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_owner_id = "+ownerId+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account acct = new Account();
				acct.setAccountId(result.getInt("account_id"));
				acct.setBalance(result.getInt("balance"));
				int as = result.getInt("as_status");
				acct.setStatusAS(asDAO.findById(as));
				int at = result.getInt("at_type");
				acct.setTypeAT(atDAO.findById(at));						
				acct.setAccountOwner(result.getInt("account_owner_id"));
				
				list.add(acct);
			}
				return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
