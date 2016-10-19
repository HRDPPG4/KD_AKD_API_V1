package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.Savelist;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.SavelistService;
import org.khmeracademy.akd.services.impl.SavelistServiceImpl;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SavelistController {
	@Autowired
	private SavelistService savelistService;
	
	@RequestMapping(value="/savelist",method=RequestMethod.GET)
	public ResponseList<Savelist> findAll(Paging pagination)
	{
		ArrayList<Object> list=savelistService.findAll(pagination);
		ResponseList<Savelist> res=new ResponseList<Savelist>();
		
		if(savelistService.findAll(pagination)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(list);
			res.setPaging(pagination);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}

	
	@RequestMapping(value="/savelist/{id}",method=RequestMethod.GET)
	public ResponseObject<Savelist> fineOne(@PathVariable("id") int id)
	{
		Savelist list=savelistService.findOne(id);
		ResponseObject<Savelist> res=new ResponseObject<Savelist>();
		if(savelistService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(list);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	
	@RequestMapping(value="/savelist/deleteSavelistDetail/{docID}",method=RequestMethod.DELETE)
	public Response deleteSavelistDetail(@PathVariable("docID") String docID)
	{
	
		boolean status=savelistService.deleteSavelistDetail(docID);
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
	@RequestMapping(value="/getuserSavelist/{userID}",method=RequestMethod.GET)
	public ResponseObject<Savelist> findSavelistByUserID (@PathVariable("userID") int userID)
	{
		ArrayList<Savelist> list=savelistService.findSavelistByUserID(userID);
		ResponseObject<Savelist> res=new ResponseObject<Savelist>();
		if(savelistService.findSavelistByUserID(userID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(list);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	@RequestMapping(value="/getuserSavelistMenu/{userID}",method=RequestMethod.GET)
	public ResponseObject<Savelist> findSavelistMenuByUserID (@PathVariable("userID") int userID)
	{
		ArrayList<Savelist> list=savelistService.findSavelistMenuByUserID(userID);
		ResponseObject<Savelist> res=new ResponseObject<Savelist>();
		if(savelistService.findSavelistMenuByUserID(userID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(list);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	
	@RequestMapping(value="/getEachSavelist/{userID}",method=RequestMethod.GET)
	public ResponseObject<Savelist> findEachSavelistByUserID(@PathVariable("userID") int userID, @RequestParam(value="savelistID") int savelistID)
	{
		
		ArrayList<Savelist> savelist= savelistService.findEachSavelistByUserID(userID, savelistID);
		ResponseObject<Savelist> res=new ResponseObject<Savelist>();
		if(savelistService.findEachSavelistByUserID(userID, savelistID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(savelist);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	@RequestMapping(value="/savelist/{id}",method=RequestMethod.PUT)
	public Response updateStatus(@PathVariable("id") int id)
	{
	
		boolean status=savelistService.updateToDisable(id);
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
	
	
	
	@RequestMapping(value="/savelist",method=RequestMethod.POST)
	public Response insert(@RequestBody Savelist list)
	{
		Response res=new Response();
		if(savelistService.insert(list)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}		
		return res;		
	}
	
	@RequestMapping(value="/savelistDetail",method=RequestMethod.POST)
	public Response insertSavelistDetail(@RequestBody Savelist list)
	{
		Response res=new Response();
		if(savelistService.insertDetail(list)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}		
		return res;		
	}
	
	@RequestMapping(value="/saveSavelistOnly",method=RequestMethod.POST)
	public Response insertSavelistOnly(@RequestBody Savelist list)
	{
		Response res=new Response();
		if(savelistService.insertSavelistOnly(list)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}		
		return res;		
	}
	
	
	@RequestMapping(value="/savelist",method=RequestMethod.PUT)
	public Response update(@RequestBody Savelist list)
	{
		Response res=new Response();
		
		if(savelistService.update(list))
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
	
	@RequestMapping(value="/savelist/{id}",method=RequestMethod.DELETE)
	public Response deleteSavelist(@PathVariable("id") int id)
	{
	
		boolean status=savelistService.deleteSavelist(id);
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
