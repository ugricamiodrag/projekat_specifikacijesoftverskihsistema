package com.specifikacije.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.dao.impl.SaveLikeDislikeDAOimpl;
import com.specifikacije.projekat.model.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LikeDislikeController {

	 @Autowired
	 private SaveLikeDislikeDAOimpl likeDislikeService;

	    @SuppressWarnings("null")
		@PostMapping("/like")
	    public String likeEstate(HttpServletRequest request, @RequestParam Long estateId) {

	    	User obj =  (User)request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
			//Check if user already liked that estate
			User user = likeDislikeService.findOne( obj.getId(), estateId);

			//If he didnt set new like, else update the existing one
			if(user == null) {
				
					likeDislikeService.save(obj.getId(), estateId, true);
		    	
			}else {
				boolean result = likeDislikeService.findLike(user.getId(), estateId, false);
				System.out.println(result);
				if(result == false) {
					likeDislikeService.delete(obj.getId(), estateId);

				}else {
					likeDislikeService.update(obj.getId(), estateId, true);

				}
				
			}
	        // Redirect the user back to the previous page
	        return "redirect:/realestate";
	    }
	    
	    // Same as like just different parameter, instead of true, send false
		@SuppressWarnings("null")
		@PostMapping("/dislike")
	    public String dislikeEstate(HttpServletRequest request, @RequestParam Long estateId) {
	        
			User obj =  (User)request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);

			User user = likeDislikeService.findOne( obj.getId(), estateId);
			

			if(user == null) {
				likeDislikeService.save(obj.getId(), estateId, false);
				

			}else {
				boolean result = likeDislikeService.findLike(user.getId(), estateId, false);
				System.out.println(result);
				if(result == true) {
					likeDislikeService.delete(obj.getId(), estateId);

				}else {
					likeDislikeService.update(obj.getId(), estateId, false);

				}
			}
			
	        return "redirect:/realestate";
	    }

}
