package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;
import org.khmeracademy.akd.entities.Feedback;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.FeedbackService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value="/feedback",method=RequestMethod.GET)
	public ResponseList<Feedback> findAll(Paging pagination)
	{
		ArrayList<Object> feed=feedbackService.findAll(pagination);
		ResponseList<Feedback> res=new ResponseList<Feedback>();
		
		if(feedbackService.findAll(pagination)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(feed);
			res.setPaging(pagination);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/feedback/{id}",method=RequestMethod.GET)
	public ResponseObject<Feedback> fineOne(@PathVariable("id") int id)
	{
		Feedback feed=feedbackService.findOne(id);
		ResponseObject<Feedback> res=new ResponseObject<Feedback>();
		if(feedbackService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(feed);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	@RequestMapping(value="/feedback/{id}",method=RequestMethod.PUT)
	public Response delete(@PathVariable("id") int id)
	{
	
		boolean status=feedbackService.delete(id);
		Response res=new Response();
		if(status){
			res.setCode(ResponseCode.DELETE_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.DELETE_FAIL);
			res.setMessage();
		}
		return res;
	}
	
	
	
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public Response insert(@RequestBody Feedback feed)
	{
		Response res=new Response();
		if(feedbackService.insert(feed)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/feedback",method=RequestMethod.PUT)
	public Response update(@RequestBody Feedback feed)
	{
		Response res=new Response();
		
		if(feedbackService.update(feed))
		{
			res.setCode(ResponseCode.UPDATE_SUCCESS);
			res.setMessage();
		}
		else
		{
			res.setCode(ResponseCode.UPDATE_FAIL);
			res.setMessage();
		}
	
		return res;
	}
	
}
