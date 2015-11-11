package com.motionapps.GSYSocial.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.services.RatingService;


@Controller
@Path("/rating")
public class RatingController {
	
	private Long status;
	
	@Autowired
	private RatingService ratingService;

	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@POST
	@Path("/add")
	public Response addRating(RatingVO ratingVO)
	{
		status=ratingService.addRating(ratingVO);
		
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	
	@POST
	@Path("/update")
	public Response updateRating(RatingVO ratingVO)
	{
		status=ratingService.updateRating(ratingVO);
		
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/delete")
	public Response deleteRating(@QueryParam("ratingId") String ratingId)
	{
		status=ratingService.deleteRating(ratingId);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	@GET
	@Path("/getRatingsByPost")
	public Response getAllRatingsByPost(@QueryParam("postId") String postId)
	{
		return Response.ok().entity(ratingService.getAllRatingsByPost(postId)).build();
	}
}
