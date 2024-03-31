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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.RealEstateService;
import com.specifikacije.projekat.service.UserService;

@Repository
public class SaveLikeDislikeDAOimpl {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RealEstateService realEstateService;
	
	@Autowired
	private UserService userService;
	
	private class LikeCallBackHandler implements RowCallbackHandler {

		private Map<Long, User> users = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long userid = resultSet.getLong(index++);
			Long estateid = resultSet.getLong(index++);
			boolean likeDislike = resultSet.getBoolean(index++);
			
			User user = userService.findOne(userid);
			RealEstate estate = realEstateService.findOne(estateid);

			if (user != null) {
				
				 if (likeDislike == true) {
			            List<RealEstate> liked = user.getLikedRealEstate();
			            if (liked == null) {
			                liked = new ArrayList<>();
			                liked.add(estate);
			                user.setLikedRealEstate(liked);
			            } else {
			                liked.add(estate);
			            }
			        } else {
			            List<RealEstate> disliked = user.getDislikedRealEstate();
			            if (disliked == null) {
			                disliked = new ArrayList<>();
			                disliked.add(estate);
			                user.setDislikedRealEstate(disliked);
			            } else {
			                disliked.add(estate);
			            }
			        }
				users.put(user.getId(), user); 
			}
			
		}
		public List<User> getLikes() {
			return new ArrayList<>(users.values());
		}
}
	
	
	public void save(Long userid, Long estateid, boolean isliked) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO like_dislike (user_id, estate_id, liked_disliked) VALUES (?, ? ,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setLong(index++, userid);
				preparedStatement.setLong(index++, estateid);
				preparedStatement.setBoolean(index++, isliked);

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	}


	public User findOne(Long id, Long estateId) {
		String sql = 
				"SELECT * FROM like_dislike " +
				"WHERE user_id = ? " + 
				"AND estate_id = ? " ;
				
		
		LikeCallBackHandler rowCallbackHandler = new LikeCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id, estateId);

		 List<User> users = rowCallbackHandler.getLikes();
		    if (users.isEmpty()) {
		        return null; // No user found with the provided id and estateId
		    } else {
		        return users.get(0); // Return the first user found
		    }
		
	}
	
	public boolean findLike(Long id, Long estateId, boolean like) {
		String sql = 
				"SELECT * FROM like_dislike " +
				"WHERE user_id = ? " + 
				"AND estate_id = ? " +
				"AND liked_disliked = ?";
				
		
		LikeCallBackHandler rowCallbackHandler = new LikeCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id, estateId, like);

		 List<User> users = rowCallbackHandler.getLikes();
		    if (users.isEmpty()) {
		        return false; 
		    } else {
		        return true; 
		    }
		
	}
	
	@Transactional
	public void update(Long userid, Long estateid, boolean isLiked) {
		String sql = "UPDATE like_dislike SET liked_disliked = ? WHERE user_id = ? AND estate_id = ?";	
		jdbcTemplate.update(sql, isLiked, userid, estateid);	
	}
	
	@Transactional
	public void delete(Long userid, Long estateid) {
		String sql = "DELETE FROM like_dislike WHERE user_id = ? AND estate_id = ?";	
		jdbcTemplate.update(sql, userid, estateid);	
	}
	
	
	
	

}
