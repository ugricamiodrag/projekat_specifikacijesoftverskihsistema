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

import com.specifikacije.projekat.dao.AgencyDAO;
import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.service.AgencyOwnerService;


@Repository
@Primary
public class AgencyDAOimpl implements AgencyDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Autowired
	private AgencyOwnerService ownerService;
	
	private class AgencyCallBackHandler implements RowCallbackHandler {

		private Map<Long, Agency> agencies = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String agency_name = resultSet.getString(index++);
			Long agency_owner_id = resultSet.getLong(index++);
			
			AgencyOwner agencyOwner = ownerService.findOne(agency_owner_id);
				
			Agency agency = agencies.get(id);
			if (agency == null) {
				agency = new Agency(id, agency_name, agencyOwner, null);
				agencies.put(agency.getId(), agency); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<Agency> getAgencies() {
			return new ArrayList<>(agencies.values());
		}
}

	@Override
	public Agency findOne(Long id) {
		String sql = 
				"SELECT * FROM agency ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		AgencyCallBackHandler rowCallbackHandler = new AgencyCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getAgencies().get(0);
	}

	@Override
	public List<Agency> findAll() {
		String sql = 
				"SELECT * FROM agency ck " +
				"ORDER BY ck.id";

		AgencyCallBackHandler rowCallbackHandler = new AgencyCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAgencies();
	}

	@Override
	public void save(Agency agency) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO agency (agency_name, agency_owner_id) VALUES (?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, agency.getName());
				preparedStatement.setLong(index++, agency.getOwner().getId());
				
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}

	@Transactional
	@Override
	public void update(Agency agency) {
		String sql = "UPDATE agency SET agency_name = ?, agency_owner_id = ? WHERE id = ?";	
		jdbcTemplate.update(sql, agency.getName(), agency.getOwner().getId(), agency.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM agency WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public Agency findOwner(Long ownerId) {
		String sql = 
				"SELECT * FROM agency ck " +
				"WHERE ck.agency_owner_id = ? " + 
				"ORDER BY ck.id";

		AgencyCallBackHandler rowCallbackHandler = new AgencyCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, ownerId);

		List<Agency> agencies = rowCallbackHandler.getAgencies();
	    if (!agencies.isEmpty()) {
	        return agencies.get(0);
	    } else {
	        return null; 
	   }
	}

	
	

	
}
