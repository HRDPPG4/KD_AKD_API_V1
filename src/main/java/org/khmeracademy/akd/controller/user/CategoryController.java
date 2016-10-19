package org.khmeracademy.akd.controller.user;

import java.util.ArrayList;

import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.response.*;
import org.khmeracademy.akd.services.CategoryService;
import org.khmeracademy.akd.services.DocumentService;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public ResponseList<Category> findAll()
	{
		ArrayList<Object> cat=categoryService.findAll();
		ResponseList<Category> res=new ResponseList<Category>();
		
		if(categoryService.findAll()!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}
	
	@RequestMapping(value="/getAllCategoryByLimit",method=RequestMethod.GET)
	public ResponseList<Category> getAllCategoryByLimit(Paging pagination)
	{
		ArrayList<Object> cat=categoryService.getAllCategoryByLimit(pagination);
		ResponseList<Category> res=new ResponseList<Category>();
		
		if(categoryService.getAllCategoryByLimit(pagination)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
			res.setPaging(pagination);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}
	
	
	@RequestMapping(value="/category/{id}",method=RequestMethod.GET)
	public ResponseObject<Category> fineOne(@PathVariable("id") String id)
	{
		Category cat=categoryService.findOne(id);
		ResponseObject<Category> res=new ResponseObject<Category>();
		if(categoryService.findOne(id)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
	@RequestMapping(value="/category/{id}",method=RequestMethod.PUT)
	public Response delete(@PathVariable("id") String id)
	{
		boolean status=categoryService.delete(id);
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
	
	
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public Response insert(@RequestBody Category cat)
	{
		Response res=new Response();
		if(categoryService.insert(cat)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage();
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage();
		}
		
		return res;
		
	}

	
	
	@RequestMapping(value="/category",method=RequestMethod.PUT)
	public Response update(@RequestBody Category cat)
	{
		Response res=new Response();
		
		if(categoryService.update(cat))
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
	
	@RequestMapping(value="/getCategoryByParentID/{ParentID}",method=RequestMethod.GET)
	public ResponseList<Category> getCategoryByParentID(@PathVariable("ParentID") String ParentID)
	{
		ArrayList<Object> cat=categoryService.getCategoryByParentID(ParentID);
		ResponseList<Category> res=new ResponseList<Category>();
		
		if(categoryService.getCategoryByParentID(ParentID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}
	
	/*@RequestMapping(value="/category/{ParentID}?{Status}",method=RequestMethod.GET)
	public ResponseList<Category> getCategoryByParentIDAndStatus(@PathVariable("ParentID") String ParentID,@PathVariable("Status") int Status)
	{
		ArrayList<Category> cat=categoryService.getCategoryByParentIDAndStatus(ParentID,Status);
		ResponseList<Category> res=new ResponseList<Category>();
		
		if(categoryService.getCategoryByParentIDAndStatus(ParentID,Status)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}*/
	
	@RequestMapping(value="/getCategoryByParentIDAndStatusEnable/{ParentID}",method=RequestMethod.GET)
	public ResponseList<Category> getCategoryByParentIDAndStatusEnable(@PathVariable("ParentID") String ParentID)
	{
		ArrayList<Category> cat=categoryService.getCategoryByParentIDAndStatusEnable(ParentID);
		ResponseList<Category> res=new ResponseList<Category>();
		
		if(categoryService.getCategoryByParentIDAndStatusEnable(ParentID)!=null){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setData(cat);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
				
		return res;
	}
	
	@RequestMapping(value="/getCategoryCount",method=RequestMethod.GET)
	public Response getCategoryCount()
	{
		int count =categoryService.getCategoryCount()-1;
		Response res=new Response();
		if(categoryService.getCategoryCount()!=0){
			res.setCode(ResponseCode.RECORD_FOUND);
			res.setMessage();
			res.setCount(count);
		}
		else{
			res.setCode(ResponseCode.RECORD_NOT_FOUND);
			res.setMessage();
		}
		
		return res;
	}
	
}
