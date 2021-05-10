package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	private static RoleDAO roleDAO = new RoleDAOImpl();

	@Override
	public List<User> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * From bankuser;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<User> list = new ArrayList<>();

			while (result.next()) {
				User user = new User();
				user.setUserId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("user_password"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				int r = result.getInt("user_role");
				user.setUserRole(roleDAO.findById(r));

				list.add(user);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bankuser WHERE username = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			User user = new User();

			while (result.next()) {
				user.setUserId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("user_password"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				int r = result.getInt("user_role");
				user.setUserRole(roleDAO.findById(r));
			}

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(User user) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bankuser (username, user_password, firstname, lastname, email, user_role)"
					+ "VALUES(?,?,?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			if (user.getUserRole() != null) {
				statement.setInt(++index, user.getUserRole().getRoleId());
			} else {
				statement.setNull(++index, java.sql.Types.INTEGER);
			}

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findById(int id) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bankuser WHERE user_id = "+id+";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			User user = new User();

			while (result.next()) {

				user.setUserId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("user_password"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));	
				int r = result.getInt("user_role");
				user.setUserRole(roleDAO.findById(r));

			}

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean deleteUser(int id) {	
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM bankuser WHERE user_id = " + id + ";";
			Statement statement = conn.createStatement();
			statement.execute(sql);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bankuser SET username = ?, user_password = ?, firstname = ?, lastname = ?, email = ?, user_role = ? WHERE user_id = ?;";		

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			if (user.getUserRole() != null) {
				statement.setInt(++index, user.getUserRole().getRoleId());
			} else {
				statement.setNull(++index, java.sql.Types.INTEGER);
			}

			statement.setInt(++index, user.getUserId());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
