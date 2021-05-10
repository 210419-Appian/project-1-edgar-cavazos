package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO{

	@Override
	public List<Role> findAll() {

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "Select * From user_role;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Role> list = new ArrayList<>();
			
			while(result.next()) {
	// did this part different from class demo - demo used the constructor
				Role role = new Role();
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("user_role"));
				
				list.add(role);
			}
			
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findByRole(String role) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role WHERE user_role = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, role);

			ResultSet result = statement.executeQuery();

			Role userrole = new Role();

			while (result.next()) {
				userrole.setRoleId(result.getInt("role_id"));
				userrole.setRole(result.getString("user_role"));
							
			}
			return userrole;
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public Role findById(int id) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM role_of_user WHERE role_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			Role role = new Role();
			
			while(result.next()) {
				
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("role_user"));
				
			}
			
			return role;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
	
