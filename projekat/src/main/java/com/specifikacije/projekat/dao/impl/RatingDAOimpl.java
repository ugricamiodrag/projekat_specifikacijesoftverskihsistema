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

import com.specifikacije.projekat.dao.RatingDAO;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.Rating;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.UserService;
import com.specifikacije.projekat.service.impl.AgentServiceImpl;
import com.specifikacije.projekat.service.impl.UserServiceImpl;

@Repository
@Primary
public class RatingDAOimpl implements RatingDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class RatingCallBackHandler implements RowCallbackHandler {

		private Map<Long, Rating> ratings = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			Long user_id = resultSet.getLong(index++);
			
			UserService userService = new UserServiceImpl();
			User user = userService.findOne(user_id);
			
			Long agent_id = resultSet.getLong(index++);
			
			AgentService agentService = new AgentServiceImpl();
			Agent agent = agentService.findOne(agent_id);
			
			Double grade = resultSet.getDouble(index++);
			String rating_comment = resultSet.getString(index++);
				
			Rating rating = ratings.get(id);
			if (rating == null) {
				rating = new Rating(id, user, agent, grade, rating_comment);
				ratings.put(rating.getId(), rating); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<Rating> getRatings() {
			return new ArrayList<>(ratings.values());
		}
	}
	
	
	@Override
	public Rating findOne(Long id) {
		String sql = 
				"SELECT * FROM rating ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		RatingCallBackHandler rowCallbackHandler = new RatingCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getRatings().get(0);
	}

	@Override
	public List<Rating> findAll() {
		String sql = 
				"SELECT * FROM rating ck " +
				"ORDER BY ck.id";

		RatingCallBackHandler rowCallbackHandler = new RatingCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getRatings();
	}

	@Override
	public void save(Rating rating) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO rating (user_id, agent_id, grade, rating_comment) VALUES (?, ? ,?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setLong(index++, rating.getUser().getId());
				preparedStatement.setLong(index++, rating.getAgent().getId());
				preparedStatement.setDouble(index++, rating.getGrade());
				preparedStatement.setString(index++, rating.getComment());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}

	@Transactional
	@Override
	public void update(Rating rating) {
		String sql = "UPDATE rating SET user_id = ?, agent_id = ?, grade = ?, rating_comment = ? WHERE id = ?";	
		jdbcTemplate.update(sql, rating.getUser().getId(), rating.getAgent().getId(), rating.getGrade(), rating.getComment(), rating.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM rating WHERE id = ?";
		jdbcTemplate.update(sql, id);	
	}

}
