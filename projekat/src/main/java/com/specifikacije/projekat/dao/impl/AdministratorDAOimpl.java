package com.specifikacije.projekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

import com.specifikacije.projekat.dao.AdministratorDAO;
import com.specifikacije.projekat.model.Administrator;

@Repository
@Primary
public class AdministratorDAOimpl implements AdministratorDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class AdministratorCallBackHandler implements RowCallbackHandler {

		private Map<Long, Administrator> admins = new LinkedHashMap<>();
		
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
			Boolean isActive = resultSet.getBoolean(index++);
				
			Administrator admin = admins.get(id);
			if (admin == null) {
				admin = new Administrator(id, first_name, surname, username, password, phone, email, address, isActive);
				admins.put(admin.getId(), admin); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<Administrator> getAdministrators() {
			return new ArrayList<>(admins.values());
		}
}

	@Override
	public Administrator findOne(Long id) {
		String sql = 
				"SELECT * FROM administrator ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		AdministratorCallBackHandler rowCallbackHandler = new AdministratorCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getAdministrators().get(0);
	}

	@Override
	public List<Administrator> findAll() {
		String sql = 
				"SELECT * FROM administrator ck " +
				"ORDER BY ck.id";

		AdministratorCallBackHandler rowCallbackHandler = new AdministratorCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAdministrators();
	}

	@Override
	public void save(Administrator admin) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO administrator (first_name, surname, username, password, phone, email, address, isActive) VALUES (?, ? ,?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, admin.getName());
				preparedStatement.setString(index++, admin.getSurname());
				preparedStatement.setString(index++, admin.getUsername());
				preparedStatement.setString(index++, admin.getPassword());
				preparedStatement.setString(index++, admin.getPhoneNumber());
				preparedStatement.setString(index++, admin.getEmail());
				preparedStatement.setString(index++, admin.getAddress());
				preparedStatement.setBoolean(index++, admin.isActive());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}

	@Transactional
	@Override
	public void update(Administrator admin) {
		String sql = "UPDATE administrator SET first_name = ?, surname = ?, username = ?, password = ?, phone = ?, email = ?, address = ?, isActive = ? WHERE id = ?";	
		jdbcTemplate.update(sql, admin.getName(), admin.getSurname(), admin.getUsername(), admin.getPassword(), admin.getPhoneNumber(), admin.getEmail(), admin.getAddress(), admin.isActive(), admin.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM administrator WHERE id = ?";
		jdbcTemplate.update(sql, id);	
	}
}
