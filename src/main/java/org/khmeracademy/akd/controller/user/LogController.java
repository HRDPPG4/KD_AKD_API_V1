package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Log;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/log",method=RequestMethod.GET)
	public ResponseList<Log> findAll()
	{
		ArrayList<Object> log=logService.findAll();
		ResponseList<Log> res=new ResponseList<Log>();
		
		if(logService.findAll()!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(log);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	

	
	
	@RequestMapping(value="user/log/{userID}",method=RequestMethod.GET)
	public ResponseList<Log> findAllByUser(@PathVariable("userID") int userID)
	{
		ArrayList<Log> log=logService.findAllByUser(userID);
		ResponseList<Log> res=new ResponseList<Log>();
		
		if(logService.findAllByUser(userID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(log);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/log/{id}",method=RequestMethod.DELETE)
	public Response delete(@PathVariable("id") int id)
	{
	
		boolean status=logService.delete(id);
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
	
	
	
	@RequestMapping(value="/log",method=RequestMethod.POST)
	public Response insert(@RequestBody Log log)
	{
		Response res=new Response();
		if(logService.insert(log)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/log",method=RequestMethod.PUT)
	public Response update(@RequestBody Log log)
	{
		Response res=new Response();
		
		if(logService.update(log))
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
	
	@RequestMapping(value="/deleteAllLogByUserID/{userID}",method=RequestMethod.DELETE)
	public Response deleteAllLogByUserID(@PathVariable("userID") int userID)
	{
	
		boolean status=logService.deleteAllLogByUserID(userID);
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
	
}
