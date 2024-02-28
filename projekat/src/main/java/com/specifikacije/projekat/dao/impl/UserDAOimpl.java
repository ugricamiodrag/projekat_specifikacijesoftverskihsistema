package com.specifikacije.projekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.specifikacije.projekat.dao.UserDAO;
import com.specifikacije.projekat.model.User;

@Repository
@Primary
public class UserDAOimpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class UserCallBackHandler implements RowCallbackHandler {

		private Map<Long, User> users = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String first_name = resultSet.getString(index++);
			String surname = resultSet.getString(index++);
			String username = resultSet.getString(index++);
			String password = resultSet.getString(index++);
			String phone = resultSet.getString(index++);
			String email = resultSet.getString(index++);
			String address = resultSet.getString(index++);
			boolean isActive = resultSet.getBoolean(index++);
				
			User user = users.get(id);
			if (user == null) {
				user = new User(id, first_name, surname, username, password, phone, email, address, isActive);
				users.put(user.getId(), user); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<User> getUsers() {
			return new ArrayList<>(users.values());
		}
}
	
	@Override
	public User findOne(Long id) {
		String sql = 
				"SELECT * FROM users ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		UserCallBackHandler rowCallbackHandler = new UserCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getUsers().get(0);
	}
	
	

	@Override
	public List<User> findAll() {
		String sql = 
				"SELECT * FROM users ck " +
				"ORDER BY ck.id";

		UserCallBackHandler rowCallbackHandler = new UserCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getUsers();
	}

	@Override
	public void save(User user) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO users (first_name, surname, username, password, phone, email, address, isActive) VALUES (?, ?, ?, ? ,?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, user.getName());
				preparedStatement.setString(index++, user.getSurname());
				preparedStatement.setString(index++, user.getUsername());
				preparedStatement.setString(index++, user.getPassword());
				preparedStatement.setString(index++, user.getPhoneNumber());
				preparedStatement.setString(index++, user.getEmail());
				preparedStatement.setString(index++, user.getAddress());
				preparedStatement.setBoolean(index++, user.isActive());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}

	@Transactional
	@Override
	public void update(User user) {
		String sql = "UPDATE users SET first_name = ?, surname = ?, username = ?, password = ?, phone = ?, email = ?, address = ? isActive = ? WHERE id = ?";	
		jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.isActive(), user.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM users WHERE id = ?";
		jdbcTemplate.update(sql, id);	
	}



	@Override
	public User findByUsername(String username) {
		String sql = 
				"SELECT * FROM users ck " +
				"WHERE ck.username = ? " + 
				"ORDER BY ck.username";

		UserCallBackHandler rowCallbackHandler = new UserCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, username);

		List<User> users = rowCallbackHandler.getUsers();
		    if (!users.isEmpty()) {
		        return users.get(0);
		    } else {
		        return null; 
		   }
	}



	@Override
	public boolean usernameExists(String username) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
	    return count != null && count > 0;
	}

	@Override
	public boolean emailExists(String email) {
		String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
	    return count != null && count > 0;
	}

}
