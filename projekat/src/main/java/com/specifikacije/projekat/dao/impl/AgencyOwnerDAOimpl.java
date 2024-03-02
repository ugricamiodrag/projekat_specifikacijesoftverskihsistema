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

import com.specifikacije.projekat.dao.AgencyOwnerDAO;
import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.User;

@Repository
@Primary
public class AgencyOwnerDAOimpl implements AgencyOwnerDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class AgencyOwnerCallBackHandler implements RowCallbackHandler {

		private Map<Long, AgencyOwner> owners = new LinkedHashMap<>();
		
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
			Boolean isBlocked = resultSet.getBoolean(index++);

			AgencyOwner owner = owners.get(id);
			if (owner == null) {
				owner = new AgencyOwner(id, first_name, surname, username, password, phone, email, address, isActive, isBlocked);
				owners.put(owner.getId(), owner); // dodavanje u kolekciju
			}
//			TODO Kada uradimo bazu
		}
		public List<AgencyOwner> getAgencyOwners() {
			return new ArrayList<>(owners.values());
		}
}
	
	@Override
	public AgencyOwner findOne(Long id) {
		String sql = 
				"SELECT * FROM agency_owner ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		AgencyOwnerCallBackHandler rowCallbackHandler = new AgencyOwnerCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		 List<AgencyOwner> users = rowCallbackHandler.getAgencyOwners();
		    if (!users.isEmpty()) {
		        return users.get(0);
		    } else {
		        return null; 
		   }	}

	@Override
	public AgencyOwner findByUsername(String username) {
		String sql = 
				"SELECT * FROM agency_owner ck " +
				"WHERE ck.username = ? " + 
				"ORDER BY ck.username";

		AgencyOwnerCallBackHandler rowCallbackHandler = new AgencyOwnerCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, username);

		List<AgencyOwner> users = rowCallbackHandler.getAgencyOwners();
	    if (!users.isEmpty()) {
	        return users.get(0);
	    } else {
	        return null; 
	   }	}
	
	@Override
	public List<AgencyOwner> findAll() {
		String sql = 
				"SELECT * FROM agency_owner ck " +
				"WHERE ck.isActive = true "
				+ "ORDER BY ck.id";

		AgencyOwnerCallBackHandler rowCallbackHandler = new AgencyOwnerCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAgencyOwners();
	}

	@Override
	public void save(AgencyOwner agencyOwner) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO agency_owner (first_name, surname, username, password, phone, email, address, isActive, isBlocked) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, agencyOwner.getName());
				preparedStatement.setString(index++, agencyOwner.getSurname());
				preparedStatement.setString(index++, agencyOwner.getUsername());
				preparedStatement.setString(index++, agencyOwner.getPassword());
				preparedStatement.setString(index++, agencyOwner.getPhoneNumber());
				preparedStatement.setString(index++, agencyOwner.getEmail());
				preparedStatement.setString(index++, agencyOwner.getAddress());
				preparedStatement.setBoolean(index, agencyOwner.isActive());
				preparedStatement.setBoolean(index++, agencyOwner.isBlocked());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}

	@Transactional
	@Override
	public void update(AgencyOwner agencyOwner) {
		String sql = "UPDATE agency_owner SET first_name = ?, surname = ?, username = ?, password = ?, phone = ?, email = ?, address = ?, isActive = ?, isBlocked = ? WHERE id = ?";	
		jdbcTemplate.update(sql, agencyOwner.getName(), agencyOwner.getSurname(), agencyOwner.getUsername(), agencyOwner.getPassword(), agencyOwner.getPhoneNumber(), agencyOwner.getEmail(), agencyOwner.getAddress(), agencyOwner.isActive(), agencyOwner.isBlocked(), agencyOwner.getId());
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "UPDATE agency_owner SET isActive = false WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public boolean usernameExists(String username) {
		String sql = "SELECT COUNT(*) FROM agency_owner WHERE username = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
	    return count != null && count > 0;
	}

	@Override
	public boolean emailExists(String email) {
		String sql = "SELECT COUNT(*) FROM agency_owner WHERE email = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
	    return count != null && count > 0;
	}

	

	

}
