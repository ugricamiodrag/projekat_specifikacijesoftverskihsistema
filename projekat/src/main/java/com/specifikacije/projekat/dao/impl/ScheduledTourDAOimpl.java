package com.specifikacije.projekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
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

import com.specifikacije.projekat.dao.ScheduledTourDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.ScheduledTour;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.UserService;
import com.specifikacije.projekat.service.RealEstateService;

@Repository
@Primary
public class ScheduledTourDAOimpl implements ScheduledTourDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RealEstateService estateService;
	
	private class ScheduledTourCallBackHandler implements RowCallbackHandler {

		private Map<Long, ScheduledTour> tours = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			LocalDateTime start_time = resultSet.getObject(index++, LocalDateTime.class);
			LocalDateTime end_time = resultSet.getObject(index++, LocalDateTime.class);
			Long user_id = resultSet.getLong(index++);
			
			User user = userService.findOne(user_id);
			
			Long estate_id = resultSet.getLong(index++);
			
			RealEstate estate = estateService.findOne(estate_id);
			
			Boolean isApproved = resultSet.getBoolean(index++);
				
			ScheduledTour tour = tours.get(id);
			if (tour == null) {
				tour = new ScheduledTour(id, start_time, end_time, user, estate, isApproved);
				tours.put(tour.getId(), tour); // dodavanje u kolekciju
			}
			
//			TODO Kada uradimo bazu
		}
		public List<ScheduledTour> getScheduledTours() {
			return new ArrayList<>(tours.values());
		}
}

	@Override
	public ScheduledTour findOne(Long id) {
		String sql = 
				"SELECT * FROM scheduled_tour ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		ScheduledTourCallBackHandler rowCallbackHandler = new ScheduledTourCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getScheduledTours().get(0);
	}

	@Override
	public List<ScheduledTour> findAll() {
		String sql = 
				"SELECT * FROM scheduled_tour ck " +
				"ORDER BY ck.id";

		ScheduledTourCallBackHandler rowCallbackHandler = new ScheduledTourCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getScheduledTours();
	}

	@Override
	public void save(ScheduledTour scheduledTour) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO scheduled_tour (start_time, end_time, user_id, estate_id, is_approved) VALUES (?, ? ,?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setTimestamp(index++, Timestamp.valueOf(scheduledTour.getStartTime()));
				preparedStatement.setTimestamp(index++, Timestamp.valueOf(scheduledTour.getEndTime()));
				preparedStatement.setLong(index++, scheduledTour.getUser().getId());
				preparedStatement.setLong(index++, scheduledTour.getRealEstate().getId());
				Boolean isApproved = scheduledTour.getIsApproved();
				if (scheduledTour.getIsApproved() != null) {
				    preparedStatement.setBoolean(index++, isApproved);
				} else {
				    preparedStatement.setNull(index++, Types.BOOLEAN);
				}
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		 if (uspeh) {
		        Number generatedId = keyHolder.getKey();
		        if (generatedId != null) {
		            scheduledTour.setId(generatedId.longValue());
		        } else {
		            throw new IllegalStateException("Failed to retrieve generated ID for ScheduledTour");
		        }
		    } else {
		        throw new IllegalStateException("Failed to save ScheduledTour");
		    }
	}
	
	@Transactional
	@Override
	public void update(ScheduledTour scheduledTour) {
		String sql = "UPDATE scheduled_tour SET start_time = ?, end_time = ?, user_id = ?, estate_id = ?, is_approved = ? WHERE id = ?";	
		jdbcTemplate.update(sql, Timestamp.valueOf(scheduledTour.getStartTime()), Timestamp.valueOf(scheduledTour.getEndTime()), scheduledTour.getUser().getId(), scheduledTour.getRealEstate().getId(), scheduledTour.getIsApproved(), scheduledTour.getId());	
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM scheduled_tour WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public ScheduledTour findByUserAndEstate(Long userid, Long estateid) {
		String sql = 
				"SELECT * FROM scheduled_tour ck " +
				"WHERE ck.user_id = ? AND ck.estate_id = ?";
				

		ScheduledTourCallBackHandler rowCallbackHandler = new ScheduledTourCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, userid, estateid);

		 List<ScheduledTour> scheduledTours = rowCallbackHandler.getScheduledTours();
		    if (scheduledTours.isEmpty()) {
		        return null; 
		    } else {
		        return scheduledTours.get(0); 
		    }
		
	}
}
