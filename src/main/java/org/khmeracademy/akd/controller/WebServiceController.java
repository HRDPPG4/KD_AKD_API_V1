/*package org.khmeracademy.akd.controller;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Userss;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class WebServiceController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ResponseList<Userss> findAll()
	{
		ArrayList<Userss> users=userService.findAll();
		ResponseList<Userss> res=new ResponseList<Userss>();
		
		if(userService.findAll()!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(users);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseObject<Userss> fineOne(@PathVariable("id") int id)
	{
		Userss user=userService.findOne(id);
		ResponseObject<Userss> res=new ResponseObject<Userss>();
		if(userService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(user);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public Response delete(@PathVariable("id") int id)
	{
	
		boolean status=userService.delete(id);
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
	
	
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public Response insert(@RequestBody Userss user)
	{
		Response res=new Response();
		if(userService.insert(user)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	public Response update(@RequestBody Userss user)
	{
		Response res=new Response();
		
		if(userService.update(user))
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
*/