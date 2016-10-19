package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.Comment;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.CategoryService;
import org.khmeracademy.akd.services.CommentService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/comment",method=RequestMethod.GET)
	public ResponseList<Comment> findAll(Paging pagination)
	{
		ArrayList<Object> com=commentService.findAll(pagination);
		ResponseList<Comment> res=new ResponseList<Comment>();
		
		if(commentService.findAll(pagination)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(com);
			res.setPaging(pagination);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/comment/{id}",method=RequestMethod.GET)
	public ResponseObject<Comment> fineOne(@PathVariable("id") int id)
	{
		Comment com=commentService.findOne(id);
		ResponseObject<Comment> res=new ResponseObject<Comment>();
		if(commentService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(com);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	@RequestMapping(value="/comment/{id}",method=RequestMethod.PUT)
	public Response delete(@PathVariable("id") int id)
	{
	
		boolean status=commentService.delete(id);
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
	
	
	
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public Response insert(@RequestBody Comment com)
	{
		Response res=new Response();
		if(commentService.insert(com)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/comment",method=RequestMethod.PUT)
	public Response update(@RequestBody Comment com)
	{
		Response res=new Response();
		
		if(commentService.update(com))
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
	
	@RequestMapping(value="/getAllCommentByDocID/{DocID}",method=RequestMethod.GET)
	public ResponseList<Comment> getAllCommentByDocID(@PathVariable("DocID") String DocID)
	{
		ArrayList<Comment> com=commentService.getAllCommentByDocID(DocID);
		ResponseList<Comment> res=new ResponseList<Comment>();
		
		if(commentService.getAllCommentByDocID(DocID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(com);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}
	
}
