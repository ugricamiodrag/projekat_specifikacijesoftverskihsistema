package com.specifikacije.projekat.dao;

import java.util.List;
import java.util.Map;

import com.specifikacije.projekat.model.RealEstate;

public interface LikeDislikeDAO {


	Map<String, List<RealEstate>> findAllLikedEstateForUserAndSetAttributes(Long userId);

}
