package com.specifikacije.projekat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.LikeDislikeDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.LikeDislikeService;


@Service
public class LikeDislikeServiceImpl implements LikeDislikeService{

	@Autowired
	LikeDislikeDAO likeDislikeService;
	
	@Override
	public Map<String, List<RealEstate>> findAllLikedEstateForUserAndSetAttributes(Long userId) {
		
		return likeDislikeService.findAllLikedEstateForUserAndSetAttributes(userId);
	}

	

}
