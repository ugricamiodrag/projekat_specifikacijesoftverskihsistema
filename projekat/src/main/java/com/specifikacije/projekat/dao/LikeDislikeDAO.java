package com.specifikacije.projekat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;

public interface LikeDislikeDAO {


	Map<String, List<RealEstate>> findAllLikedEstateForUserAndSetAttributes(Long userId);

}
