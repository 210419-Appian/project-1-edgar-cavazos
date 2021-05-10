package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.utils.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO{

	@Override
	public List<AccountStatus> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_status;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<AccountStatus> list = new ArrayList<>();
			
			while(result.next()) {
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setStatusId(result.getInt("status_id"));
				accountStatus.setStatus(result.getString("status"));
				
				list.add(accountStatus);
				
			}
			
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addAccountStatus(AccountStatus as) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO account_status (status)"+"VALUES(?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, as.getStatus());
			
			statement.execute();
		
		return true;
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public AccountStatus findById(int id) {
		
try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM account_status WHERE status_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			AccountStatus acctStatus = new AccountStatus();
			
			while(result.next()) {
				acctStatus.setStatusId(result.getInt("status_id"));
				acctStatus.setStatus(result.getString("status"));
			}
			
			return acctStatus;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	

}
