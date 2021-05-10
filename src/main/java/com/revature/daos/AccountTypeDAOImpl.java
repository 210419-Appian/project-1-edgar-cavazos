package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountType;
import com.revature.utils.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO{

	@Override
	public List<AccountType> findAll() {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_type;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<AccountType> list = new ArrayList<>();
			
			while(result.next()) {
				AccountType accountType = new AccountType();
				accountType.setTypeId(result.getInt("type_id"));
				accountType.setType(result.getString("type_"));
				
				list.add(accountType);
				
			}
			
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addAccountType(AccountType a) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO account_type (type_)"+"VALUES(?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, a.getType());
			
			statement.execute();
		
		return true;
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public AccountType findById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account_type WHERE type_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			AccountType acctType = new AccountType();
			
			while(result.next()) {
				acctType.setTypeId(result.getInt("type_id"));
				acctType.setType(result.getString("type_"));
			}
			
			return acctType;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
