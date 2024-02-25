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

import com.specifikacije.projekat.dao.AgentDAO;
import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.service.AgencyService;
import com.specifikacije.projekat.service.impl.AgencyServiceImpl;

@Repository
@Primary
public class AgentDAOimpl implements AgentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AgencyService agencyService;
	
	private class AgentCallBackHandler implements RowCallbackHandler {

		private Map<Long, Agent> agents = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String first_name = resultSet.getString(index++);
			String surname = resultSet.getString(index++);
			String phone = resultSet.getString(index++);
			String email = resultSet.getString(index++);
			String address = resultSet.getString(index++);
			Long agencyId = resultSet.getLong(index++);

			Agency agency = agencyService.findOne(agencyId);
			
			Agent agent = agents.get(id);
			if (agent == null) {
				agent = new Agent(id, first_name, surname, phone, email, address, null, agency);
				agents.put(agent.getId(), agent); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<Agent> getAgents() {
			return new ArrayList<>(agents.values());
		}
}
	
	@Override
	public Agent findOne(Long id) {
		String sql = 
				"SELECT * FROM agent ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		AgentCallBackHandler rowCallbackHandler = new AgentCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getAgents().get(0);
	}

	@Override
	public List<Agent> findAll() {
		String sql = 
				"SELECT * FROM agent ck " +
				"ORDER BY ck.id";

		AgentCallBackHandler rowCallbackHandler = new AgentCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAgents();
	}

	@Override
	public void save(Agent agent) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO agent (first_name, surname, phone, email, address, agency_id) VALUES (?, ? ,?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, agent.getName());
				preparedStatement.setString(index++, agent.getSurname());
				preparedStatement.setString(index++, agent.getPhoneNumber());
				preparedStatement.setString(index++, agent.getEmail());
				preparedStatement.setString(index++, agent.getAddress());
				preparedStatement.setLong(index++, agent.getAgency().getId());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}
	
	@Transactional
	@Override
	public void update(Agent agent) {
		String sql = "UPDATE agent SET first_name = ?, surname = ?, phone = ?, email = ?, address = ?, agency_id = ? WHERE id = ?";	
		jdbcTemplate.update(sql, agent.getName(), agent.getSurname(), agent.getPhoneNumber(), agent.getEmail(), agent.getAddress(), agent.getAgency().getId(), agent.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM agent WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

}
