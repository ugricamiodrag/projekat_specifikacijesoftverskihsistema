package com.specifikacije.projekat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.specifikacije.projekat.dao.LikeDislikeDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.RealEstateService;
import com.specifikacije.projekat.service.UserService;


@Repository
public class LikeDislikeDAOimpl implements LikeDislikeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RealEstateService realEstateService;
	
	private class LikeDislikeCallBackHandler implements RowCallbackHandler  {

		
		private Map<Long, RealEstate> likes = new LinkedHashMap<>();
	    private User user;

		public LikeDislikeCallBackHandler(User user) {
		        this.user = user;
		    }
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long estateId = resultSet.getLong(index++);
			Boolean likeDislike = resultSet.getBoolean(index++);

			RealEstate estate = realEstateService.findOne(estateId);
			
			
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
		    }
		
		
		
		public List<RealEstate> getLikes() {
			return new ArrayList<>(likes.values());
		}
}
	
	
	@Override
	 public Map<String, List<RealEstate>> findAllLikedEstateForUserAndSetAttributes(Long userId) {
		
		//Only for getting users liked and disliked realestates, for saving and updating check SaveLikeDislikeDAOimpl
		
	    Map<String, List<RealEstate>> likedAndDislikedEstates = new HashMap<>();
        User user = userService.findOne(userId);
        if (user != null) {
            String sql = "SELECT estate_id, liked_disliked FROM like_dislike WHERE user_id = ?";
            LikeDislikeCallBackHandler rowCallbackHandler = new LikeDislikeCallBackHandler(user);
            jdbcTemplate.query(sql, rowCallbackHandler, userId);

            List<RealEstate> likedEstates = user.getLikedRealEstate();
            List<RealEstate> dislikedEstates = user.getDislikedRealEstate();

            likedAndDislikedEstates.put("likedEstates", likedEstates);
            likedAndDislikedEstates.put("dislikedEstates", dislikedEstates);
        }
        return likedAndDislikedEstates;
    }


	

}
