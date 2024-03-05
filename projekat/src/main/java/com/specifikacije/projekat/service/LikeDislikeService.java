package com.specifikacije.projekat.service;

import java.util.List;
import java.util.Map;

import com.specifikacije.projekat.model.RealEstate;

public interface LikeDislikeService {

	Map<String, List<RealEstate>> findAllLikedEstateForUserAndSetAttributes(Long userId);
	
}
